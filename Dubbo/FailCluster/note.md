Dubbo ��һ�������ܵķֲ�ʽ�����ܣ��ṩ�˶��ּ�Ⱥ�ݴ���ԣ������ڷֲ�ʽ�����д���������ʧ�ܵ������ȷ������ĸ߿����Ժ��ȶ��ԡ������� Dubbo �г����ļ�Ⱥ�ݴ���Լ��乤��ԭ��

### 1. Failover Cluster��ʧ���Զ��л���

- **����ԭ��**�����������ʧ��ʱ�����Զ��л����������õķ����ṩ�߽������ԡ�Ĭ�����Դ���Ϊ 2 �Σ��ܹ��᳢�Ե��� 3 �Ρ����Ի��ڲ�ͬ�ķ����ṩ��֮����У�ֱ�����óɹ����ߴﵽ������Դ�����
- **���ó���**�������ڶ����������ݵȵ�д��������Ϊ���Կ��ܻᵼ���ظ�ִ����ͬ�Ĳ��������磬��ѯ���ݿ��е����ݣ���β�ѯ��������ݲ���Ӱ�졣

### 2. Failfast Cluster������ʧ�ܣ�

- **����ԭ��**��ֻ����һ�η�����ã�������ʧ���������׳��쳣������������ԡ�
- **���ó���**�������ڷ��ݵȵ�д���������������ݣ���Ϊ�ظ����ÿ��ܻᵼ�������ظ����롣

### 3. Failsafe Cluster��ʧ�ܰ�ȫ��

- **����ԭ��**�����������ʧ��ʱ��ֱ�Ӻ����쳣������һ���ս������ `null`�������������ԡ�
- **���ó���**�������ڶԵ��ý�������еĳ��������¼��־�Ȳ�������ʹ����ʧ��Ҳ����Ӱ��ϵͳ����Ҫ���ܡ�

### 4. Failback Cluster��ʧ���Զ��ָ���

- **����ԭ��**�����������ʧ��ʱ���Ὣʧ�ܵ������¼������Ȼ���ں�̨��ʱ���ԣ�ֱ�����óɹ���
- **���ó���**�������ڶ�����һ����Ҫ��ϸߣ�����ʵʱ��Ҫ�󲻸ߵĳ��������첽��Ϣ���ͣ���ʹ��ǰ����ʧ�ܣ��������������ԡ�

### 5. Forking Cluster�����е��ö�������ṩ�ߣ�

- **����ԭ��**�����е��ö�������ṩ�ߣ�ֻҪ��һ�����óɹ����������ؽ��������ͨ������ `forks` ������ָ�����е��õķ����ṩ��������
- **���ó���**�������ڶ�ʵʱ��Ҫ��ϸߵĳ�������ʵʱ��ѯ�������Դ��ͨ�����е��ÿ�����߲�ѯ�ٶȡ�

### 6. Broadcast Cluster���㲥�������з����ṩ�ߣ�

- **����ԭ��**��������㲥�����еķ����ṩ�ߣ�������ã��κ�һ�������ṩ�ߵ���ʧ������Ϊ�ôε���ʧ�ܡ�
- **���ó���**��������֪ͨ���з����ṩ�߸��»��桢���õȳ�����

### ����ʾ��

����������� Dubbo �����ü�Ⱥ�ݴ���Ե�ʾ����



xml











```xml
<dubbo:Reference id="helloService" interface="com.example.HelloService" cluster="failover" retries="2"/>
```



��������ͨ�� `cluster` ����ָ���˼�Ⱥ�ݴ����Ϊ `failover`����ͨ�� `retries` ����ָ�������Դ���Ϊ 2 �Ρ�



java











```java
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import com.example.HelloService;

public class Consumer {
    public static void main(String[] args) {
        // Ӧ������
        ApplicationConfig application = new ApplicationConfig();
        application.setName("consumer");

        // ����Զ�̷���
        ReferenceConfig<HelloService> Reference = new ReferenceConfig<>();
        Reference.setApplication(application);
        Reference.setInterface(HelloService.class);
        Reference.setCluster("failover");
        Reference.setRetries(2);

        // ��ȡԶ�̷������
        HelloService helloService = Reference.get();

        // ִ��Զ�̷���
        String result = helloService.sayHello("World");
        System.out.println(result);
    }
}
```







���� Java ����ͨ����̵ķ�ʽ������ `failover` ��Ⱥ�ݴ���ԣ���ָ�������Դ���Ϊ 2 �Ρ�



ͨ����Щ��Ⱥ�ݴ���ԣ�Dubbo �ܹ��ڷֲ�ʽ��������Ч�ش���������ʧ�ܵ���������ϵͳ�Ŀ����Ժ��ȶ��ԡ�