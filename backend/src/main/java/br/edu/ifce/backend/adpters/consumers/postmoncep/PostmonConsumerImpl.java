package br.edu.ifce.backend.adpters.consumers.postmoncep;

import br.edu.ifce.backend.domain.ports.driven.PostmonConsumer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class PostmonConsumerImpl implements PostmonConsumer {

    private final WebClient webClientPostmon;

    @Override
    public ZipInformation getZipInformation(String zip) {
        return webClientPostmon.get()
                .uri("/cep/{zip}", zip)
                .retrieve()
                .bodyToMono(PostmonConsumer.ZipInformation.class)
                .block();
    }
}
