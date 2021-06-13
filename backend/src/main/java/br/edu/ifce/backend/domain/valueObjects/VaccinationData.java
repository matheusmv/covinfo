package br.edu.ifce.backend.domain.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VaccinationData {

    private String countryName;
    private String stateName;
    private String cityName;
    private Long numberOfPeopleVaccinatedInTheCountry;
    private Long numberOfPeopleVaccinatedInTheState;
    private Long numberOfPeopleVaccinatedInTheCity;
}
