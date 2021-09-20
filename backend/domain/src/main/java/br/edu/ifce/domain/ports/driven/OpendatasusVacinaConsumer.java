package br.edu.ifce.domain.ports.driven;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public interface OpendatasusVacinaConsumer {

    VaccinationRates obtainVaccinationRates(String stateAcronym, String cityName);

    @Getter
    @Setter
    @AllArgsConstructor
    class VaccinationRates {
        private Long totalInTheCountry;
        private Long totalInTheState;
        private Long totalInTheCity;
    }
}
