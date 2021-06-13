package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.valueObjects.VaccinationData;

public interface GetDataFromTheVaccinationCampaign {
    VaccinationData execute();
}
