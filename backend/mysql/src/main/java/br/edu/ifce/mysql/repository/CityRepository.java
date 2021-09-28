package br.edu.ifce.mysql.repository;

import br.edu.ifce.domain.City;

import java.util.Optional;

public interface CityRepository {

    Optional<City> findByNameAndStateInitials(String name, String stateInitials);
}
