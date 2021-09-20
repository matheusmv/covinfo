package br.edu.ifce.app.adpters.consumers.opendatasus;

import br.edu.ifce.domain.ports.driven.OpendatasusLeitosConsumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OpendatasusLeitosConsumerImpl implements OpendatasusLeitosConsumer {

    private final WebClient webClientOpendatasusLeitos;

    @Override
    public List<MedicalCareUnits> listMedicalCareUnits(String stateName, String cityName) {
        var medicalCareUnitsQuery = new QueryObjects().medicalCareUnitsQuery(stateName, cityName);

        return findMedicalCareUnits(medicalCareUnitsQuery)
                .map(hits -> hits.getHitsArray().getSources().stream()
                        .map(Source::getMedicalCareUnity)
                        .map(this::toMedicalCareUnits)
                        .collect(Collectors.toList()))
                .block();
    }

    private Mono<Hits> findMedicalCareUnits(String query) {
        return webClientOpendatasusLeitos.post()
                .uri("/_search")
                .bodyValue(query)
                .retrieve()
                .bodyToMono(Hits.class);
    }

    private MedicalCareUnits toMedicalCareUnits(MedicalCareUnity medicalCareUnity) {
        var cnes = Optional.ofNullable(medicalCareUnity.getCnes()).orElse("CNES NOT INFORMED");
        var name = Optional.ofNullable(medicalCareUnity.getNomeCnes()).orElse("NAME NOT INFORMED");

        return new MedicalCareUnits(cnes, name);
    }

    @Getter
    private static final class Hits {
        @JsonProperty("hits")
        private HitsArray hitsArray;
    }

    @Getter
    private static final class HitsArray {
        @JsonProperty("hits")
        private List<Source> sources;
    }

    @Getter
    private static final class Source {
        @JsonProperty("_source")
        private MedicalCareUnity medicalCareUnity;
    }

    @Getter
    private static final class MedicalCareUnity {
        @JsonProperty("cnes")
        private String cnes;
        @JsonProperty("nomeCnes")
        private String nomeCnes;
    }
}
