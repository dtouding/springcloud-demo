package com.dtouding.feignconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "hello-service", fallback = HelloServiceFallback.class)
public interface HelloService {

    @GetMapping("/hello")
    String hello();

}
