package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.Country;

import java.util.List;

public interface GetAllCountries {
    List<Country> execute();
}
