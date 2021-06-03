package br.edu.ifce.backend.adpters.db.jpa;

import br.edu.ifce.backend.domain.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityJpaRepository extends JpaRepository<City, Long> {
    @Transactional(readOnly = true)
    Optional<City> findByName(String name);

    @Transactional(readOnly = true)
    List<City> findByNameContaining(String name);
}
