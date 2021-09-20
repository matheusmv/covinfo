package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.ports.dto.CompleteZipCodeInformation;

public interface GetInformationAboutZipCode {
    CompleteZipCodeInformation execute(String zip);
}
