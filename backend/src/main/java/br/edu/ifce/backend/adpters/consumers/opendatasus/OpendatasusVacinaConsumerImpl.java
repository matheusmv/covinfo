package br.edu.ifce.backend.adpters.consumers.opendatasus;

import br.edu.ifce.backend.domain.ports.driven.OpendatasusVacinaConsumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OpendatasusVacinaConsumerImpl implements OpendatasusVacinaConsumer {

    private final WebClient webClientOpendatasusVacina;

    @Override
    public VaccinationRates obtainVaccinationRates(String stateAcronym, String cityName) {
        var countryQuery = new QueryObjects().countryQuery();
        var stateQuery = new QueryObjects().stateQuery(stateAcronym);
        var cityQuery = new QueryObjects().cityQuery(stateAcronym, cityName);

        var numbersInTheCountry = retrieve(countryQuery).bodyToMono(CountryTotal.class);
        var numbersInTheState = retrieve(stateQuery).bodyToMono(StateTotal.class);
        var numbersInTheCity = retrieve(cityQuery).bodyToMono(CityTotal.class);

        return Mono.zip(numbersInTheCountry, numbersInTheState, numbersInTheCity)
                .map(tuple -> new VaccinationRates(tuple.getT1().countryTotal, tuple.getT2().stateTotal, tuple.getT3().cityTotal))
                .block();
    }

    private WebClient.ResponseSpec retrieve(String query) {
        return webClientOpendatasusVacina.post()
                .uri("/_count")
                .bodyValue(query)
                .retrieve();
    }

    @Getter
    private static class CountryTotal {
        @JsonProperty("count")
        private Long countryTotal;
    }

    @Getter
    private static class StateTotal {
        @JsonProperty("count")
        private Long stateTotal;
    }

    @Getter
    private static class CityTotal {
        @JsonProperty("count")
        private Long cityTotal;
    }
}
