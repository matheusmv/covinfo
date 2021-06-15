package br.edu.ifce.backend.domain.valueObjects;

import br.edu.ifce.backend.domain.ports.driven.OpendatasusLeitosConsumer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MedicalCareUnityInfo {

    private String countryName;
    private String stateName;
    private String cityName;
    private List<OpendatasusLeitosConsumer.MedicalCareUnits> units;
}
