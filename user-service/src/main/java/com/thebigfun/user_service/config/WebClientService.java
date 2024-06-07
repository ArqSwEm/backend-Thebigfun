package com.thebigfun.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Service
public class WebClientService {
    @Bean
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }


}
