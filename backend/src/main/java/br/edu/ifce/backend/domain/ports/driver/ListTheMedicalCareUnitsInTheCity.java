package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.valueObjects.MedicalCareUnityInfo;

import java.util.List;

public interface ListTheMedicalCareUnitsInTheCity {
    List<MedicalCareUnityInfo> execute();
}
