package br.edu.ifce.usecase.ports.responses;

import br.edu.ifce.domain.City;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompleteZipCodeInformation {
    private final String zip;
    private final City city;
}
