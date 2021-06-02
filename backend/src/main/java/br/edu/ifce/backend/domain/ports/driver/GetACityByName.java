package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.City;

public interface GetACityByName {
    City execute(String name);
}
