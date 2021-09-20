package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryJpaRepository extends JpaRepository<Country, Long> {

}
