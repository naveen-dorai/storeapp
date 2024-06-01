package com.ser.storeapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class AppConfig{
    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}