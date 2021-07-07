package br.edu.ifce.backend.domain.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
