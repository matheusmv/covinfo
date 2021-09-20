package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.ports.dto.VaccinationData;

public interface GetDataFromTheVaccinationCampaign {
    VaccinationData execute();
}
