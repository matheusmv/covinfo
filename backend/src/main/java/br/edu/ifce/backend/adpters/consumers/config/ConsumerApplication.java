package br.edu.ifce.backend.adpters.consumers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConsumerApplication {

    @Bean
    public WebClient webClientPostmon(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.postmon.com.br")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
