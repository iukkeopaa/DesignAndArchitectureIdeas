在 Dubbo 里配置服务消费者的引用，有 XML 配置、注解配置和 Java API 配置三种常见方式，下面为你详细介绍这三种配置方式：

### XML 配置方式

#### 步骤

1. **引入依赖**：在项目的 `pom.xml` 里添加 Dubbo 以及相关依赖。



xml











```xml
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo</artifactId>
    <version>2.7.15</version>
</dependency>
```



1. **创建服务接口**：定义服务提供者所实现的接口。



java











```java
// 服务接口
public interface HelloService {
    String sayHello(String name);
}
```



1. **配置消费者**：在 `applicationContext.xml` 文件中配置 Dubbo 消费者。



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

    <!-- 应用信息 -->
    <dubbo:application name="consumer-demo"/>

    <!-- 注册中心地址 -->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>

    <!-- 引用远程服务 -->
    <dubbo:Reference id="helloService" interface="com.example.HelloService" />
</beans>
```



1. **使用服务**：在 Java 代码里获取服务引用并调用服务。



java











```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloService helloService = (HelloService) context.getBean("helloService");
        String result = helloService.sayHello("World");
        System.out.println(result);
    }
}
```

### 注解配置方式

#### 步骤

1. **引入依赖**：和 XML 配置方式一样，要在 `pom.xml` 里添加 Dubbo 及相关依赖。
2. **创建服务接口**：定义服务提供者所实现的接口。



java











```java
// 服务接口
public interface HelloService {
    String sayHello(String name);
}
```



1. **配置消费者**：使用 `@DubboReference` 注解来引用远程服务。



java











```java
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {
    @DubboReference
    private HelloService helloService;

    public String callHelloService(String name) {
        return helloService.sayHello(name);
    }
}
```



1. **启动 Spring 应用**：在 Spring Boot 项目里，直接启动应用即可。



java











```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
        ConsumerService consumerService = context.getBean(ConsumerService.class);
        String result = consumerService.callHelloService("World");
        System.out.println(result);
    }
}
```

### Java API 配置方式

#### 步骤

1. **引入依赖**：同样要在 `pom.xml` 里添加 Dubbo 及相关依赖。
2. **创建服务接口**：定义服务提供者所实现的接口。



java











```java
// 服务接口
public interface HelloService {
    String sayHello(String name);
}
```



1. **配置消费者**：通过 Java 代码配置 Dubbo 消费者。



java











```java
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class Consumer {
    public static void main(String[] args) {
        // 应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("consumer-demo");

        // 注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        // 引用远程服务
        ReferenceConfig<HelloService> Reference = new ReferenceConfig<>();
        Reference.setApplication(application);
        Reference.setRegistry(registry);
        Reference.setInterface(HelloService.class);

        // 获取服务代理
        HelloService helloService = Reference.get();
        String result = helloService.sayHello("World");
        System.out.println(result);
    }
}
```







上述三种方式都能实现服务消费者的引用配置，你可以依据项目的实际状况和个人偏好来选择合适的配置方式。


