package com.thebigfun.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Service
public class WebClientService {
//agremos Bean para una instancia que nos retorne WEBCLIENT

    @Bean
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }


}
