package com.dtouding.feignconsumer;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceFallback implements HelloService {

    @Override
    public String hello() {
        return "ERROR";
    }
}
