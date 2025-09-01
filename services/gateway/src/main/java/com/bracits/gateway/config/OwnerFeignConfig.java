package com.bracits.gateway.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OwnerFeignConfig {

    @Bean
    public RequestInterceptor propagationInterceptor() {
        return requestTemplate -> {
            String traceparent = MDC.get("traceparent");
            if (traceparent != null) requestTemplate.header("traceparent", traceparent);
        };
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
