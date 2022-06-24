package com.microservices.demo.kafka.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Shahrooz on 6/24/2022.
 */
@Configuration
public class WebClientConfig {

    @Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }
}
