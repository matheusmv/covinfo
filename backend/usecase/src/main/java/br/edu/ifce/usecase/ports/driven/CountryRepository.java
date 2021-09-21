package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.Country;

import java.util.List;

public interface CountryRepository {
    void create(Country country);

    List<Country> listAll();

    Country findById(Long id);

    void update(Long id, Country country);

    void delete(Long id);
}
