package br.edu.ifce.mysql.repository;

import br.edu.ifce.domain.City;
import br.edu.ifce.mysql.Page;
import br.edu.ifce.mysql.PageRequest;

import java.util.Optional;

public interface CityRepository {

    City create(City city);

    City update(City city);

    Optional<City> find(Long id);

    Page<City> find(PageRequest page);

    void delete(City city);

    void delete(Long id);
}
