package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.usecase.ports.responses.CompleteZipCodeInformation;

public interface GetInformationAboutZipCode {
    CompleteZipCodeInformation execute(String zip);
}
