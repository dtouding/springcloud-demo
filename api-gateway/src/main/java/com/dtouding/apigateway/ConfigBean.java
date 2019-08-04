package com.dtouding.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
