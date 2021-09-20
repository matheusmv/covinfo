package br.edu.ifce.app.adpters.consumers.postmoncep;

import br.edu.ifce.app.adpters.consumers.exceptions.ZipNotFoundException;
import br.edu.ifce.domain.ports.driven.PostmonConsumer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class PostmonConsumerImpl implements PostmonConsumer {

    private final WebClient webClientPostmon;

    @Override
    public ZipInformation getZipInformation(String zip) {
        try {
            return webClientPostmon.get()
                    .uri("/cep/{zip}", zip)
                    .retrieve()
                    .bodyToMono(PostmonConsumer.ZipInformation.class)
                    .block();
        } catch (Exception e) {
            throw new ZipNotFoundException("We did not find data related to this zip code: " + zip);
        }
    }
}
