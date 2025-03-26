HashedWheelTimer 是 Netty 框架提供的一个定时任务调度器，基于时间轮（Hashed Wheel）算法实现。下面从数据结构、任务添加、时间轮转动、任务执行和任务取消几个方面详细介绍其实现原理。

### 数据结构

#### 时间轮

时间轮本质上是一个固定大小的数组，数组的每个元素称为一个槽（Slot）。每个槽可以存放一个链表，链表中存储了该槽对应的定时任务。时间轮的大小（槽的数量）通常是 2 的幂次方，这样可以通过位运算来高效地计算任务应该被放入哪个槽中。

#### 定时任务

每个定时任务用一个 `HashedWheelTimeout` 对象表示，该对象包含了任务的执行逻辑、任务的到期时间、所在的时间轮、所在的槽等信息。

### 任务添加

当有新的定时任务需要添加到 `HashedWheelTimer` 中时，执行步骤如下：



1. **计算任务的剩余轮数和目标槽**：根据任务的延迟时间和时间轮的刻度（`tickDuration`，即时间轮每次转动的时间间隔），计算出任务需要等待的 tick 数。通过这个 tick 数可以得到任务的剩余轮数（即任务需要在时间轮上转动多少圈后才会到期）和目标槽（即任务最终会被放入哪个槽中）。
2. **将任务放入目标槽**：把 `HashedWheelTimeout` 对象添加到目标槽对应的链表中。

### 时间轮转动

`HashedWheelTimer` 内部有一个后台线程负责驱动时间轮的转动，具体过程如下：



1. **初始化**：在启动 `HashedWheelTimer` 时，后台线程会被启动。该线程会按照固定的时间间隔（`tickDuration`）进行转动。

2. **推进 tick**：每次转动时，时间轮的 tick 数会加 1。

3. 检查当前槽中的任务

   ：当时间轮转动到某个槽时，会检查该槽中链表的所有任务。对于每个任务，会检查其剩余轮数：

    - 如果剩余轮数大于 0，说明任务还需要在时间轮上再转动若干圈才会到期，将剩余轮数减 1。
    - 如果剩余轮数等于 0，说明任务已经到期，将任务从链表中移除，并将其标记为已过期。

### 任务执行

对于标记为已过期的任务，`HashedWheelTimer` 会将其放入一个任务队列中，由专门的线程池来执行这些任务。这样做的好处是可以避免任务执行时间过长影响时间轮的正常转动。线程池会从任务队列中取出任务并执行其 `run()` 方法。

### 任务取消

如果需要取消某个定时任务，可以调用 `HashedWheelTimeout` 对象的 `cancel()` 方法。该方法会将任务从所在的槽链表中移除，并标记任务为已取消。在时间轮转动时，会跳过这些已取消的任务。

### 示例代码（Java）

java











```java
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

public class HashedWheelTimerExample {
    public static void main(String[] args) {
        // 创建一个 HashedWheelTimer 实例，时间轮刻度为 100 毫秒
        HashedWheelTimer timer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);

        // 定义一个定时任务
        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("定时任务执行：" + System.currentTimeMillis());
            }
        };

        // 安排任务在 3 秒后执行
        timer.newTimeout(task, 3, TimeUnit.SECONDS);

        try {
            // 主线程休眠 5 秒，确保任务有足够的时间执行
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 停止时间轮
        timer.stop();
    }
}
```



在上述代码中，首先创建了一个 `HashedWheelTimer` 实例，然后定义了一个定时任务 `TimerTask`，并使用 `newTimeout()` 方法安排任务在 3 秒后执行。最后，主线程休眠 5 秒，确保任务有足够的时间执行，之后停止时间轮。



综上所述，`HashedWheelTimer` 通过时间轮算法和后台线程的配合，实现了高效的定时任务调度功能。它利用固定大小的数组和链表结构来存储任务，通过不断转动时间轮来检查任务是否到期，并使用线程池来执行到期的任务，从而在处理大量定时任务时具有较好的性能表现。


## Netty 的时间轮 HashedWheelTimer 给出了一个粗略的定时器实现，之所以称之为粗略的实现是因为该时间轮并没有严格的准时执行定时任务，而是在每隔一个时间间隔之后的时间节点执行，并执行当前时间节点之前到期的定时任务。


## HashedWheelTimer 内部的数据结构

HashedWheelTimer 的主体数据结构 wheel 是一个由多个链表所组成的数组，默认情况下该数组的大小为 512。当定时任务准备加入到时间轮中的时候，将会以其等待执行的时间为依据选择该数组上的一个具体槽位上的链表加入。


在这个 wheel 数组中，每一个槽位都是一条由 HashedWheelTimeout 所组成的链表，其中链表中的每一个节点都是一个等待执行的定时任务。


## HashedWheelTimer 内部的线程模型

### 时间轮驱动线程

- **作用**：该线程是 HashedWheelTimer 的核心驱动，负责推动时间轮按照固定的时间间隔（`tickDuration`）转动。它的主要任务是不断更新时间轮的 `tick` 计数，检查当前 `tick` 对应的槽中是否有任务到期，并对到期任务进行相应处理。

- 工作流程

    - **初始化**：在创建并启动 `HashedWheelTimer` 时，会启动这个时间轮驱动线程。

    - **周期性转动**：线程进入一个无限循环，在每次循环中，线程会休眠 `tickDuration` 时间，然后将时间轮的 `tick` 计数加 1。

    - 任务检查

      ：根据更新后的



    ```
    tick
    ```

     

    找到对应的槽，遍历该槽中的任务链表。对于每个任务，检查其剩余轮数：

    - 若剩余轮数大于 0，将剩余轮数减 1。
    - 若剩余轮数等于 0，表明任务已到期，将任务从链表移除，并将其标记为已过期，同时放入任务执行队列。

- **异常处理**：在整个过程中，如果出现异常（如线程被中断），线程会进行相应的异常处理，确保时间轮的稳定运行。

### 任务执行线程池

- **作用**：专门负责执行到期的定时任务。之所以使用线程池来执行任务，是为了避免单个任务执行时间过长而影响时间轮的正常转动，保证时间轮能够按照固定的时间间隔持续推进。
- 工作流程
    - **任务接收**：时间轮驱动线程将到期的任务放入任务执行队列后，线程池会从该队列中获取任务。
    - **任务执行**：线程池中的线程从队列中取出任务，调用任务的 `run()` 方法来执行具体的业务逻辑。
    - **资源管理**：线程池会对线程资源进行管理，根据任务的数量和执行情况动态调整线程的数量，以提高资源利用率和系统性能。

### 两者协作机制

- 时间轮驱动线程专注于时间轮的转动和任务的到期检查，将到期任务放入任务执行队列，而不负责任务的具体执行。
- 任务执行线程池则专注于从队列中获取任务并执行，与时间轮驱动线程解耦，保证了时间轮的转动不受任务执行时间的影响。

### 示例代码理解

java











```java
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

public class HashedWheelTimerThreadModelExample {
    public static void main(String[] args) {
        // 创建一个 HashedWheelTimer 实例，时间轮刻度为 100 毫秒
        HashedWheelTimer timer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);

        // 定义一个定时任务
        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("定时任务执行：" + System.currentTimeMillis());
            }
        };

        // 安排任务在 3 秒后执行
        timer.newTimeout(task, 3, TimeUnit.SECONDS);

        try {
            // 主线程休眠 5 秒，确保任务有足够的时间执行
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 停止时间轮
        timer.stop();
    }
}
```







在这个示例中，`HashedWheelTimer` 启动后，时间轮驱动线程开始按照 100 毫秒的间隔转动时间轮。当任务的延迟时间到达后，时间轮驱动线程发现任务到期，将其放入任务执行队列，线程池中的线程会从队列中取出该任务并执行 `run()` 方法，输出任务执行的时间信息。


### 解释2


简单看到 HashedWheelTimer 内部的 woker 线程的 run()方法，在其首先会记录启动时间作为 startTime 作为接下来调度定时任务的时间依据，而之后会通过 CountDownLatch 来通知所有外部线程当前 worker 工作线程已经初始化完毕。之后的循环体便是当时间轮持续生效的时间里的具体调度逻辑。时间刻度是时间轮的一个重要属性，其默认为 100ms，此处的循环间隔便是时间轮的时间刻度，默认情况下就是间隔 100ms 进行一次调度循环。工作线程会维护当前工作线程具体循环了多少轮，用于定位具体执行触发时间轮数组上的哪一个位置上的链表。当时间轮准备 shutdown 的阶段，最后的代码会对未执行的任务整理到未执行的队列中。

由此可见，worker 线程的 run()方法中基本定义了工作线程的整个生命周期，从初始的初始化到循环体中的具体调度，最后到未执行任务的具体清理。整体的调度逻辑便主要在这里执行。值得注意的是，在这里的前提下，每个 HashedWheelTimer 时间轮都会有一个工作线程进行调度，所以不需要在 netty 中在每一个连接中单独使用一个 HashedWheelTimer 来进行定时任务的调度，否则可能将对性能产生影响