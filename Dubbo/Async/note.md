
�� Dubbo ��첽�����ܹ�����ϵͳ�Ĳ���������������Ӧ���ܣ�������ͬ�����ö������̡߳������ԭ��ʹ�÷�ʽ��ע������������ Dubbo �첽���õ�ʵ�֡�

### ʵ��ԭ��

#### 1. �����

����˴�������ʱ���Ὣ����Ĵ����߼��ŵ��̳߳����첽ִ�У���������˵� I/O �߳̾���Ѹ���ͷţ�ȥ�����������󡣵���������ɺ󣬷���˻�ѽ�����ظ��ͻ��ˡ�

#### 2. �ͻ���

- **Future ģʽ**���ͻ��˷�����ú󣬲���ȴ�����˵���Ӧ��������������һ�� `Future` ���󡣿ͻ��˿�������Ҫ�����ʱ��ͨ�� `Future` ����� `get()` ��������ȡ����˵ķ��ؽ�����ڵȴ�����Ĺ����У��ͻ����߳̿���ȥ������������
- **CompletableFuture ģʽ**��Dubbo ֧��ʹ�� Java �� `CompletableFuture` �����첽���á��ͻ��˷�����ú󣬻᷵��һ�� `CompletableFuture` ���󣬿ͻ��˿���ʹ�� `CompletableFuture` �ṩ�ĸ��ַ������� `thenApply`��`thenAccept` �ȣ��Խ�������첽����ʵ�ָ����ӵ��첽����߼���

### ʹ�÷�ʽ

#### 1. ����ӿڶ���

java











```java
// ����ӿ�
public interface HelloService {
    String sayHello(String name);
}
```





#### 2. �����ṩ��ʵ��

java











```java
// ����ʵ����
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        try {
            // ģ���ʱ����
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, " + name;
    }
}
```

#### 3. �������������������

##### **XML ���÷�ʽ**

xml











```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://dubbo.apache.org/schema/dubbo
                           http://dubbo.apache.org/schema/dubbo/dubbo.xsd">

    <!-- Ӧ����Ϣ -->
    <dubbo:application name="consumer-demo"/>

    <!-- ע�����ĵ�ַ -->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>

    <!-- ����Զ�̷������� async Ϊ true �����첽���� -->
    <dubbo:reference id="helloService" interface="com.example.HelloService" async="true"/>
</beans>
```



java











```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.dubbo.rpc.service.AsyncContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.Future;

public class Consumer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloService helloService = (HelloService) context.getBean("helloService");

        // �����첽����
        helloService.sayHello("World");

        // ��ȡ Future ����
        Future<String> future = RpcContext.getContext().getFuture();

        try {
            // ��ȡ���
            String result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

##### **ע�����÷�ʽ**

java











```java
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.apache.dubbo.rpc.service.AsyncContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.Future;

@Component
public class ConsumerService {
    @DubboReference(async = true)
    private HelloService helloService;

    public void callHelloService() {
        // �����첽����
        helloService.sayHello("World");

        // ��ȡ Future ����
        Future<String> future = RpcContext.getContext().getFuture();

        try {
            // ��ȡ���
            String result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### ע������

- **�쳣����**���첽�����У��쳣�����ڵ���ʱ�����׳��������ڻ�ȡ���ʱ�׳���������Ҫ�ڻ�ȡ���ʱ�����쳣����
- **��Դ����**���첽���ÿ��ܻᴴ���������̺߳����ӣ�Ҫ���������̳߳غ����ӳأ�������Դ�ľ���
- **˳������**���첽���õĽ������˳����ܺ͵���˳��һ�£�����˳��Ҫ����Ҫ����Ĵ����߼���