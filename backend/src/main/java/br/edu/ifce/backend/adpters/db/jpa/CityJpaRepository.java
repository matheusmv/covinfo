package br.edu.ifce.backend.adpters.db.jpa;

import br.edu.ifce.backend.domain.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityJpaRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
}
