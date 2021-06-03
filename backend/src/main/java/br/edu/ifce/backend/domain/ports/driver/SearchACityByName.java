package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.City;

import java.util.List;

public interface SearchACityByName {
    List<City> execute(String name);
}
