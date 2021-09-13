package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.ports.dto.VaccinationData;

public interface GetDataFromTheVaccinationCampaign {
    VaccinationData execute();
}
