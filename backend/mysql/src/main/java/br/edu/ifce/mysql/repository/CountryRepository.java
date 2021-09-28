package br.edu.ifce.mysql.repository;

import br.edu.ifce.domain.Country;
import br.edu.ifce.mysql.Page;
import br.edu.ifce.mysql.PageRequest;

import java.util.Optional;

public interface CountryRepository {

    Country create(Country country);

    Country update(Country country);

    Optional<Country> find(Long id);

    Page<Country> find(PageRequest page);

    void delete(Country country);

    void delete(Long id);
}
