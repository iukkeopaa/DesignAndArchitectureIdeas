## Dubbo�ṩ�˰汾�����Ե�֧�֣������ڷ���Ĳ�ͬ�汾֮����е��á��汾������������Ҫ�漰������ӿڵı������������������ɾ���������޸ķ�������������������Ǵ���Dubbo�а汾�����������һЩ����
1. �ӿ������ѭ�淶: ����Ʒ���ӿ�ʱ��Ӧ����ѭ���õĽӿ����ԭ�򣬿��ǵ����ܵı���������������
�ӿ��ȶ�������Ƶ�����޸Ľӿڣ��Լ��ٰ汾�����Ե����⣬
2. ʹ�ð汾��:��Dubbo����ӿ��Ͽ������ version �ԣ��������ֲ�ͬ�汾�Ľӿڡ������ߺ��ṩ�߶�
����������ʹ�õİ汾���Ӷ��ڷ�����������н���ƥ��͵��á�
3. ʹ�÷�������: ����������߶˲�֪������Ľӿڶ��壬���߷���ӿڷ����˽ϴ�ı仯������ʹ��Dubbo
�ķ�������(Generic Service)�����÷��񡣷������ò������ھ���Ľӿڶ��壬�����ڰ汾�����������
�������� 
4. ������ģʽ: �ڷ���ӿڷ����仯ʱ����������������ģʽ��ʵ���¾ɰ汾�ļ����ԡ��ɰ汾�Ľӿڿ���ͨ��������ת�����°汾�Ľӿڵ��á�
5. ����汾ע��: ��Dubbo��ע�������У�����Ϊ��ͬ�汾�ķ����ṩ��ע�᲻ͬ��·����ʹ�������߿��Ը�
�ݰ汾ѡ�������Ӧ���ṩ�ߡ�



### �汾�Ź���

�� Dubbo �У�����Ϊ����ӿ�ָ���汾�ţ�ͨ���汾�������ֲ�ͬ�汾�ķ��������ڷ�������ʱ�������߿��Ը�����Ҫѡ��ʹ�ò�ͬ�汾�ķ���

#### ���ð汾��

�ڷ����ṩ�ߺ������ߵ�������ָ���汾�š�



**�����ṩ�����ã�XML ʾ����**



xml











```xml
<dubbo:service interface="com.example.HelloService" version="1.0.0" ref="helloService"/>
```



**�������������ã�XML ʾ����**



xml











```xml
<dubbo:reference interface="com.example.HelloService" version="1.0.0" id="helloService"/>
```

#### ʾ��˵��

���������һ���򵥵ķ���ӿ� `HelloService`��



java











```java
// �汾 1.0.0
public interface HelloService {
    String sayHello(String name);
}
```



�����ṩ��ʵ�ָýӿڣ�



java











```java
public class HelloServiceImplV1 implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
```



���������ߵ��ø÷���



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



����ҵ��չ����Ҫ�Է����������������һ��������



java











```java
// �汾 2.0.0
public interface HelloService {
    String sayHello(String name);
    String sayGoodbye(String name);
}
```



�����ṩ��ʵ���°汾�Ľӿڣ�



java











```java
public class HelloServiceImplV2 implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    @Override
    public String sayGoodbye(String name) {
        return "Goodbye, " + name;
    }
}
```



��ʱ���ɰ汾����������Ȼ����ʹ�ð汾 1.0.0 �ķ��񣬶��°汾�������߿���ʹ�ð汾 2.0.0 �ķ���

### �ӿڼ������

����Ʒ���ӿ�ʱ����ѭһЩԭ�������߽ӿڵļ����ԡ�

#### ԭ��

- **����ɾ������**�����ɾ���ӿ��еķ������ɰ汾�������ߵ��ø÷���ʱ��������⡣
- **��������Ĭ��ʵ��**������Ҫ��������ʱ������Ϊ���ṩĬ��ʵ�֣������ɰ汾�������߲���Ӱ�졣

#### ʾ��˵��

java











```java
// �汾 1.0.0
public interface HelloService {
    String sayHello(String name);
}

// �汾 2.0.0
public interface HelloService {
    String sayHello(String name);

    // �����������ṩĬ��ʵ��
    default String sayGoodbye(String name) {
        return "Goodbye, default implementation";
    }
}
```



�������ɰ汾���������ڵ��� `HelloService` ʱ�������ܵ�����������Ӱ�졣

### �Ҷȷ���

�Ҷȷ�����һ���𲽽��°汾�����������������ķ�ʽ��������Ч���Ͱ汾��������������ķ��ա�

#### ����

1. **���������л�**���Ƚ�һС���������л����°汾�ķ����ϣ��۲��Ƿ��м��������⡣
2. **������Χ**�����û�����⣬������ʹ���°汾�����������Χ��ֱ��ȫ���л���ɡ�

#### ʾ��˵��

����ͨ�� Dubbo ��·�ɹ�����ʵ�ֻҶȷ��������磬�����û� ID ���������з֣��������û�������·�ɵ��°汾�ķ����ϣ�



xml











```xml
<dubbo:reference interface="com.example.HelloService" version="2.0.0" id="helloServiceV2">
    <dubbo:parameter key="router" value="condition"/>
    <dubbo:parameter key="rule" value="host = 10.20.153.10 => version = 2.0.0"/>
</dubbo:reference>
```







�������ý����� `10.20.153.10` ����������·�ɵ��汾 2.0.0 �ķ����ϡ�



ͨ�����Ϸ�����������Ч�ش��� Dubbo �еİ汾���������⣬ȷ�������ƽ���������ȶ����С�