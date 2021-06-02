package br.edu.ifce.backend.domain.valueObjects;

import br.edu.ifce.backend.domain.entities.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompleteZipCodeInformation {

    private String zip;
    private City city;
}
