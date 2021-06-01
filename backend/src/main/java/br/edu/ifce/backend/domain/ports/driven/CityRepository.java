package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.City;

import java.util.List;

public interface CityRepository {
    void create(City city);

    List<City> listAll();

    City findById(Long id);

    void update(Long id, City city);

    void delete(Long id);
}
