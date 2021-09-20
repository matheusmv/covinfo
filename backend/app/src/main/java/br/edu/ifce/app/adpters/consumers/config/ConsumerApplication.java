package br.edu.ifce.app.adpters.consumers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConsumerApplication {

    @Value("${credentials.postmon-cep.url}")
    private String apiPostmonUrl;

    @Value("${credentials.opendatasus.api-vacina.url}")
    private String apiVacinaUrl;
    @Value("${credentials.opendatasus.api-vacina.username}")
    private String apiVacinaUsername;
    @Value("${credentials.opendatasus.api-vacina.password}")
    private String apiVacinaPassword;

    @Value("${credentials.opendatasus.api-leitos.url}")
    private String apiLeitosUrl;
    @Value("${credentials.opendatasus.api-leitos.username}")
    private String apiLeitosUsername;
    @Value("${credentials.opendatasus.api-leitos.password}")
    private String apiLeitosPassword;

    @Bean
    public WebClient webClientPostmon(WebClient.Builder builder) {
        return builder
                .baseUrl(apiPostmonUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient webClientOpendatasusVacina(WebClient.Builder builder) {
        return builder
                .baseUrl(apiVacinaUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(ExchangeFilterFunctions.basicAuthentication(apiVacinaUsername, apiVacinaPassword))
                .build();
    }

    @Bean
    public WebClient webClientOpendatasusLeitos(WebClient.Builder builder) {
        return builder
                .baseUrl(apiLeitosUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(ExchangeFilterFunctions.basicAuthentication(apiLeitosUsername, apiLeitosPassword))
                .build();
    }
}
