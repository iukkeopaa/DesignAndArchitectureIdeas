�� Dubbo �����÷��������ߵ����ã��� XML ���á�ע�����ú� Java API �������ֳ�����ʽ������Ϊ����ϸ�������������÷�ʽ��

### XML ���÷�ʽ

#### ����

1. **��������**������Ŀ�� `pom.xml` ����� Dubbo �Լ����������



xml











```xml
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo</artifactId>
    <version>2.7.15</version>
</dependency>
```



1. **��������ӿ�**����������ṩ����ʵ�ֵĽӿڡ�



java











```java
// ����ӿ�
public interface HelloService {
    String sayHello(String name);
}
```



1. **����������**���� `applicationContext.xml` �ļ������� Dubbo �����ߡ�



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

    <!-- ����Զ�̷��� -->
    <dubbo:Reference id="helloService" interface="com.example.HelloService" />
</beans>
```



1. **ʹ�÷���**���� Java �������ȡ�������ò����÷���



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

### ע�����÷�ʽ

#### ����

1. **��������**���� XML ���÷�ʽһ����Ҫ�� `pom.xml` ����� Dubbo �����������
2. **��������ӿ�**����������ṩ����ʵ�ֵĽӿڡ�



java











```java
// ����ӿ�
public interface HelloService {
    String sayHello(String name);
}
```



1. **����������**��ʹ�� `@DubboReference` ע��������Զ�̷���



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



1. **���� Spring Ӧ��**���� Spring Boot ��Ŀ�ֱ������Ӧ�ü��ɡ�



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

### Java API ���÷�ʽ

#### ����

1. **��������**��ͬ��Ҫ�� `pom.xml` ����� Dubbo �����������
2. **��������ӿ�**����������ṩ����ʵ�ֵĽӿڡ�



java











```java
// ����ӿ�
public interface HelloService {
    String sayHello(String name);
}
```



1. **����������**��ͨ�� Java �������� Dubbo �����ߡ�



java











```java
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class Consumer {
    public static void main(String[] args) {
        // Ӧ������
        ApplicationConfig application = new ApplicationConfig();
        application.setName("consumer-demo");

        // ע����������
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        // ����Զ�̷���
        ReferenceConfig<HelloService> Reference = new ReferenceConfig<>();
        Reference.setApplication(application);
        Reference.setRegistry(registry);
        Reference.setInterface(HelloService.class);

        // ��ȡ�������
        HelloService helloService = Reference.get();
        String result = helloService.sayHello("World");
        System.out.println(result);
    }
}
```







�������ַ�ʽ����ʵ�ַ��������ߵ��������ã������������Ŀ��ʵ��״���͸���ƫ����ѡ����ʵ����÷�ʽ��


