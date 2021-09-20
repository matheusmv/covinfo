package br.edu.ifce.domain.ports.dto;

import br.edu.ifce.domain.entities.City;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompleteZipCodeInformation {
    private final String zip;
    private final City city;
}
