package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.Country;

public interface GetACountryById {
    Country execute(Long id);
}
