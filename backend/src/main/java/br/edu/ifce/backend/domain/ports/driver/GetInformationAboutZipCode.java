package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.valueObjects.CompleteZipCodeInformation;

public interface GetInformationAboutZipCode {
    CompleteZipCodeInformation execute(String zip);
}
