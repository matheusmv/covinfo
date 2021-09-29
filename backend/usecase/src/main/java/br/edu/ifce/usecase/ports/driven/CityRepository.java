package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.City;

import java.util.Optional;

public interface CityRepository {

    Optional<City> findByNameAndStateInitials(String name, String stateInitials);
}
