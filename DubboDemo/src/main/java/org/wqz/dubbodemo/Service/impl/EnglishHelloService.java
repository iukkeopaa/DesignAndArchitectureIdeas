package org.wqz.dubbodemo.Service.impl;

import org.wqz.dubbodemo.Service.HelloService;

public class EnglishHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "Hello!";
    }
}    