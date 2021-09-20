package br.edu.ifce.domain.ports.dto;

import br.edu.ifce.domain.ports.driven.OpendatasusLeitosConsumer;
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
