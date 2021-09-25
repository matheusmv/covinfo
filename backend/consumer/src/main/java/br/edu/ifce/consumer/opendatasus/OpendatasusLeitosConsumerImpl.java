package br.edu.ifce.consumer.opendatasus;

import br.edu.ifce.usecase.ports.driven.OpendatasusLeitosConsumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OpendatasusLeitosConsumerImpl implements OpendatasusLeitosConsumer {

    private final WebClient webClientOpendatasusLeitos;

    @Override
    public List<MedicalCareUnits> listMedicalCareUnits(String stateName, String cityName) {
        var medicalCareUnitsQuery = new QueryObjects().medicalCareUnitsQuery(stateName, cityName);

        return findMedicalCareUnits(medicalCareUnitsQuery);
    }

    private List<MedicalCareUnits> findMedicalCareUnits(String query) {
        Function<MedicalCareUnity, MedicalCareUnits> convertToMedicalCareUnits = medicalCareUnity -> {
            var cnes = Optional.ofNullable(medicalCareUnity.getCnes()).orElse("CNES NOT INFORMED");
            var name = Optional.ofNullable(medicalCareUnity.getNomeCnes()).orElse("NAME NOT INFORMED");

            return new MedicalCareUnits(cnes, name);
        };

        Function<Hits, List<MedicalCareUnits>> extractMedicalCareUnitsFromResponse = hits -> hits.getHitsArray()
                .getSources()
                .stream()
                .map(Source::getMedicalCareUnity)
                .map(convertToMedicalCareUnits)
                .collect(Collectors.toList());

        return webClientOpendatasusLeitos.post()
                .uri("/_search")
                .bodyValue(query)
                .retrieve()
                .bodyToMono(Hits.class)
                .map(extractMedicalCareUnitsFromResponse)
                .block();
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
