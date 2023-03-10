package com.kgromov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class WebConfig {
    @Bean
    public RestTemplate restTemplate(ProtobufHttpMessageConverter protobufHttpMessageConverter) {
        return new RestTemplate(List.of(protobufHttpMessageConverter));
    }

    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
}
