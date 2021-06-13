package br.edu.ifce.backend.adpters.consumers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConsumerApplication {

    @Value("${credentials.opendatasus.api-vacina.username}")
    private String apiVacinaUsername;
    @Value("${credentials.opendatasus.api-vacina.password}")
    private String apiVacinaPassword;

    @Bean
    public WebClient webClientPostmon(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.postmon.com.br")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient webClientOpendatasusVacina(WebClient.Builder builder) {
        return builder
                .baseUrl("https://imunizacao-es.saude.gov.br")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(ExchangeFilterFunctions.basicAuthentication(apiVacinaUsername, apiVacinaPassword))
                .build();
    }
}
