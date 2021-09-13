package br.edu.ifce.backend.domain.ports.dto;

import br.edu.ifce.backend.domain.entities.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CompleteZipCodeInformation {
    private final String zip;
    private final City city;
}
