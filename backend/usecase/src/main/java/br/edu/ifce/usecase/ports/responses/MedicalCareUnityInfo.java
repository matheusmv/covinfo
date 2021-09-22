package br.edu.ifce.usecase.ports.responses;

import br.edu.ifce.usecase.ports.driven.OpendatasusLeitosConsumer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MedicalCareUnityInfo {
    private final String countryName;
    private final String stateName;
    private final String cityName;
    private final List<OpendatasusLeitosConsumer.MedicalCareUnits> units;
}
