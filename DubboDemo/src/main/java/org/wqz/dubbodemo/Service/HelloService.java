package org.wqz.dubbodemo.Service;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface HelloService {
    String sayHello();
}    