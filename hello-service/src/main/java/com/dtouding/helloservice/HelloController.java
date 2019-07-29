package com.dtouding.helloservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class HelloController {

    @Resource
    private Registration registration;

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> result = new HashMap<>();
        log.info("/hello, host: {}, port: {}, instanceId: {}, serviceId: {}", registration.getHost(),
                registration.getPort(), registration.getInstanceId(), registration.getServiceId());
        result.put("当前服务实例", String.format("/hello, host: %s, port: %s, instanceId: %s, serviceId: %s",
                registration.getHost(),
                registration.getPort(),
                registration.getInstanceId(),
                registration.getServiceId()));
        result.put("所有服务实例", client.getServices().toString());
        return result;
    }
}
