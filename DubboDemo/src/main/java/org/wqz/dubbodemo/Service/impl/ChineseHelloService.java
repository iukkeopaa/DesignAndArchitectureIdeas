package org.wqz.dubbodemo.Service.impl;

import org.wqz.dubbodemo.Service.HelloService;

public class ChineseHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "你好！";
    }
}    