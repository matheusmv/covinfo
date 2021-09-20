package br.edu.ifce.app.adpters.consumers.opendatasus;

import br.edu.ifce.domain.ports.driven.OpendatasusVacinaConsumer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class OpendatasusVacinaConsumerImpl implements OpendatasusVacinaConsumer {

    private final WebClient webClientOpendatasusVacina;

    @Override
    public VaccinationRates obtainVaccinationRates(String stateAcronym, String cityName) {
        var query = new QueryObjects();

        var countryTotal = consultResults(query.countryQuery()).bodyToMono(CountryTotal.class);
        var stateTotal = consultResults(query.stateQuery(stateAcronym)).bodyToMono(StateTotal.class);
        var cityTotal = consultResults(query.cityQuery(stateAcronym, cityName)).bodyToMono(CityTotal.class);

        return Mono.zip(countryTotal, stateTotal, cityTotal).map(getVaccinationRates()).block();
    }

    private WebClient.ResponseSpec consultResults(String query) {
        return webClientOpendatasusVacina.post()
                .uri("/_count")
                .bodyValue(query)
                .retrieve();
    }

    private Function<Tuple3<CountryTotal, StateTotal, CityTotal>, VaccinationRates> getVaccinationRates() {
        return results -> new VaccinationRates(
                results.getT1().countryTotal,
                results.getT2().stateTotal,
                results.getT3().cityTotal
        );
    }

    @Getter
    private static final class CountryTotal {
        @JsonProperty("count")
        private Long countryTotal;
    }

    @Getter
    private static final class StateTotal {
        @JsonProperty("count")
        private Long stateTotal;
    }

    @Getter
    private static final class CityTotal {
        @JsonProperty("count")
        private Long cityTotal;
    }
}
