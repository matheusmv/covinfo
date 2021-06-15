package br.edu.ifce.backend.adpters.consumers.opendatasus;

import br.edu.ifce.backend.domain.ports.driven.OpendatasusLeitosConsumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OpendatasusLeitosConsumerImpl implements OpendatasusLeitosConsumer {

    private final WebClient webClientOpendatasusLeitos;

    @Override
    public List<MedicalCareUnits> listMedicalCareUnits(String stateName, String cityName) {
        var medicalCareUnitsQuery = new QueryObjects().medicalCareUnitsQuery(stateName, cityName);

        return findMedicalCareUnits(medicalCareUnitsQuery)
                .map(Hits::getHitsArray)
                .map(HitsArray::extractMedicalCareUnityList)
                .map(this::toMedicalCareUnits)
                .block();
    }

    private Mono<Hits> findMedicalCareUnits(String query) {
        return webClientOpendatasusLeitos.post()
                .uri("/_search")
                .bodyValue(query)
                .retrieve()
                .bodyToMono(Hits.class);
    }

    private List<MedicalCareUnits> toMedicalCareUnits(List<MedicalCareUnity> medicalCareUnities) {
        return medicalCareUnities.stream()
                .map(medicalCareUnity -> {
                    var cnes = medicalCareUnity.getCnes();
                    var name = Objects.isNull(medicalCareUnity.getNomeCnes()) ? "NAME NOT INFORMED" : medicalCareUnity.getNomeCnes();

                    return new MedicalCareUnits(cnes, name);
                })
                .collect(Collectors.toList());
    }

    @Getter
    private static class Hits {
        @JsonProperty("hits")
        private HitsArray hitsArray;
    }

    @Getter
    private static class HitsArray {
        @JsonProperty("hits")
        private List<Source> sources;

        public List<MedicalCareUnity> extractMedicalCareUnityList() {
            return sources.stream()
                    .map(Source::getMedicalCareUnity)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    private static class Source {
        @JsonProperty("_source")
        private MedicalCareUnity medicalCareUnity;
    }

    @Getter
    private static class MedicalCareUnity {
        @JsonProperty("cnes")
        private String cnes;
        @JsonProperty("nomeCnes")
        private String nomeCnes;
    }
}
