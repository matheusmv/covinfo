package br.edu.ifce.backend.domain.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MedicalCareUnityInfo {

    private String countryName;
    private String stateName;
    private String cityName;
    private String CNES;
    private String nameOfHealthCareUnit;
}
