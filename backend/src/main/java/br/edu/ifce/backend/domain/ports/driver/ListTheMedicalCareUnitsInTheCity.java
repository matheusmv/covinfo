package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.valueObjects.MedicalCareUnityInfo;

public interface ListTheMedicalCareUnitsInTheCity {
    MedicalCareUnityInfo execute();
}
