## 相比 ThreadLocal，FastThreadLocal 到底快在哪里

1. FastThreadLocal 在具体的定位的过程中，只需要根据在构造方法里获取得到的具体下标就可以定位到具体的数组位置进行变量的存取，而在 jdk 原生的 ThreadLocal 中，具体位置的下标获取不仅需要计算 ThreadLocal 的 hash 值，并需要在 hashTable 上根据 key 定位的结果，一旦定位之后的结果上已经存在其他 ThreadLocal 的变量，那么则是通过线性探测法，在 hashTable 上寻找下一个位置进行，相比 FastThreadLocal 定位的过程要复杂的多。
2. FastThreadLocal 由于采取数组的方式，当面对扩容的时候，只需要将原数组中的内容复制过去，并用 NULL 对象填满剩余位置即可，而在 ThreadLocal 中，由于 hashTable 的缘故，在扩容后还需要进行一轮 rehash，在这过程中，仍旧存在 hash 冲突的可能。
3. 在 FastThreadLocal 中，遍历当前线程的所有本地变量，只需要将数组首位的集合即可，不需要遍历数组上的每一个位置。
4. 在原生的 ThreadLocal 中，由于可能存在 ThreadLocal 被回收，但是当前线程仍旧存活的情况导致 ThreadLocal 对应的本地变量内存泄漏的问题，因此在 ThreadLocal 的每次操作后，都会进行启发式的内存泄漏检测，防止这样的问题产生，但也在每次操作后花费了额外的开销。而在 FastThreadLocal 的场景下，由于数组首位的 FastThreadLocal 集合中保持着所有 FastThreadLocal 对象的引用，因此当外部的 FastThreadLocal 的引用被置为 null，该 FastThreadLocal 对象仍旧保持着这个集合的引用，不会被回收掉，只需要在线程当前业务操作后，手动调用 FastThreadLocal 的 removeAll()方法，将会遍历数组首位集合，回收掉所有 FastThreadLocal 的变量，避免内存泄漏的产生，也减少了原生 ThreadLocal 的启发式检测开销。



### 数据结构与存储方式

- **ThreadLocal**：`ThreadLocal` 是 Java 标准库中的线程局部变量实现，它依赖于每个 `Thread` 对象里的 `ThreadLocalMap` 。这个 `ThreadLocalMap` 是一种特殊的哈希表，`ThreadLocal` 对象作为键，实际存储的值作为值。在进行数据的存储和读取操作时，需要通过哈希函数计算索引，而且可能会出现哈希冲突，一旦发生冲突，就需要采用开放寻址法来解决，这无疑会增加额外的时间开销。
- **FastThreadLocal**：`FastThreadLocal` 采用数组来存储数据。在 `FastThreadLocalThread` 中存在一个 `InternalThreadLocalMap`，这个 `InternalThreadLocalMap` 内部包含一个数组，`FastThreadLocal` 有一个唯一的索引，借助这个索引可以直接定位到数组中对应的位置，进行数据的存储和读取，从而避免了哈希计算和哈希冲突带来的性能损耗。

### 操作复杂度

- **ThreadLocal**：当调用 `ThreadLocal` 的 `get()` 或 `set()` 方法时，需要先获取当前线程的 `ThreadLocalMap`，然后进行哈希计算、处理哈希冲突等操作，其时间复杂度平均为 O (1)，但在最坏情况下可能会达到 O (n)，这里的 n 是 `ThreadLocalMap` 中元素的数量。
- **FastThreadLocal**：`FastThreadLocal` 的 `get()` 和 `set()` 方法通过索引直接访问数组，时间复杂度始终为 O (1)，不存在最坏情况，所以在性能上更加稳定。

### 上下文切换开销

- **ThreadLocal**：在高并发场景下，线程的上下文切换频繁，`ThreadLocalMap` 的操作会带来一定的开销，尤其是在处理哈希冲突时，会影响性能。
- **FastThreadLocal**：由于其简单的数组访问方式，`FastThreadLocal` 在上下文切换时的开销较小，能够更快地恢复线程的局部变量状态。

### 示例代码对比

下面是 `ThreadLocal` 和 `FastThreadLocal` 的简单使用示例，从中可以看出它们使用方式的不同：



java











```java
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

// ThreadLocal示例
class ThreadLocalExample {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            threadLocal.set("ThreadLocal Value");
            System.out.println("ThreadLocal value: " + threadLocal.get());
            threadLocal.remove();
        });
        thread.start();
    }
}

// FastThreadLocal示例
class FastThreadLocalExample {
    private static final FastThreadLocal<String> fastThreadLocal = new FastThreadLocal<>();

    public static void main(String[] args) {
        FastThreadLocalThread thread = new FastThreadLocalThread(() -> {
            fastThreadLocal.set("FastThreadLocal Value");
            System.out.println("FastThreadLocal value: " + fastThreadLocal.get());
            fastThreadLocal.remove();
        });
        thread.start();
    }
}
```



在这个示例里，`ThreadLocal` 是基于标准 Java 线程实现的，而 `FastThreadLocal` 则依赖于 `FastThreadLocalThread`。`FastThreadLocal` 通过数组直接访问的方式，避免了哈希计算和冲突处理，从而提升了性能。
## 介绍

`FastThreadLocal` 是 Netty 框架提供的一个工具类，用于在 Netty 线程中实现快速的线程局部变量（Thread Local）存储。它是对 Java 标准库中 `ThreadLocal` 的优化，旨在减少在高并发场景下的性能开销。

### 与 `ThreadLocal` 的对比

- **性能**：Java 标准库中的 `ThreadLocal` 在使用时会涉及到哈希表的操作，在高并发场景下可能会产生哈希冲突，从而影响性能。而 `FastThreadLocal` 通过使用数组来存储线程局部变量，避免了哈希冲突，因此在性能上更具优势。
- **使用场景**：`ThreadLocal` 适用于一般的线程局部变量存储场景，而 `FastThreadLocal` 更适合在高并发、对性能要求较高的 Netty 应用中使用。

### 示例代码

以下是一个简单的 `FastThreadLocal` 使用示例：



python











```python
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

public class FastThreadLocalExample {
    // 定义一个 FastThreadLocal 变量
    private static final FastThreadLocal<String> fastThreadLocal = new FastThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "Initial Value";
        }
    };

    public static void main(String[] args) {
        // 创建一个 FastThreadLocalThread 线程
        FastThreadLocalThread thread = new FastThreadLocalThread(() -> {
            // 获取 FastThreadLocal 变量的值
            System.out.println("Initial value: " + fastThreadLocal.get());

            // 设置新的值
            fastThreadLocal.set("New Value");
            System.out.println("New value: " + fastThreadLocal.get());

            // 移除 FastThreadLocal 变量
            fastThreadLocal.remove();
            System.out.println("Value after remove: " + fastThreadLocal.get());
        });

        // 启动线程
        thread.start();
    }
}
```





### 代码解释

1. **定义 `FastThreadLocal` 变量**：通过 `FastThreadLocal` 类创建一个线程局部变量，并通过 `initialValue` 方法设置初始值。
2. **创建 `FastThreadLocalThread` 线程**：`FastThreadLocal` 需要与 `FastThreadLocalThread` 一起使用，因为 `FastThreadLocalThread` 内部维护了一个数组来存储 `FastThreadLocal` 变量。
3. **操作 `FastThreadLocal` 变量**：在 `FastThreadLocalThread` 线程中，可以使用 `get` 方法获取变量的值，使用 `set` 方法设置新的值，使用 `remove` 方法移除变量。

### 注意事项

- `FastThreadLocal` 必须与 `FastThreadLocalThread` 或其子类一起使用，否则无法发挥其性能优势。
- 在使用完 `FastThreadLocal` 变量后，建议调用 `remove` 方法来释放资源，避免内存泄漏。
## 如何使用 FastThreadLocal


如果想要得到 FastThreadLocal 的速度优势，必须通过 FastThreadLocalThread 或者其子类的线程，才可以使用，因为这个原因，Netty 的 DefaultThreadFactory，其内部默认线程工厂的 newThread()方法就是直接初始化一个 FastThreadLocalThread ，以便期望在 ThreadLocal 的操作中，得到其性能上带来的优势。