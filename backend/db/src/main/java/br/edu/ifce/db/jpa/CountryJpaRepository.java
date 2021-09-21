package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryJpaRepository extends JpaRepository<Country, Long> {

}
