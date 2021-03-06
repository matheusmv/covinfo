package br.edu.ifce.usecase.ports.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VaccinationData {
    private final String countryName;
    private final String stateName;
    private final String cityName;
    private final Long numberOfPeopleVaccinatedInTheCountry;
    private final Long numberOfPeopleVaccinatedInTheState;
    private final Long numberOfPeopleVaccinatedInTheCity;
}
