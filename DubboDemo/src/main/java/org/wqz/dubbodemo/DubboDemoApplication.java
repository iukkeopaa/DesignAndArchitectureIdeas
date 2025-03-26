package org.wqz.dubbodemo;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wqz.dubbodemo.Service.HelloService;

@SpringBootApplication
public class DubboDemoApplication {

    public static void main(String[] args) {
        // 获取扩展加载器
        ExtensionLoader<HelloService> extensionLoader = ExtensionLoader.getExtensionLoader(HelloService.class);

        // 根据别名获取具体的实现类
        HelloService englishService = extensionLoader.getExtension("english");
        System.out.println(englishService.sayHello());

        HelloService chineseService = extensionLoader.getExtension("chinese");
        System.out.println(chineseService.sayHello());
    }
}
