package br.edu.ifce.domain.ports.driven;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface OpendatasusLeitosConsumer {

    List<MedicalCareUnits> listMedicalCareUnits(String stateName, String cityName);

    @Getter
    @Setter
    @AllArgsConstructor
    class MedicalCareUnits {
        private String cnes;
        private String nameOfHealthCareUnit;
    }
}
