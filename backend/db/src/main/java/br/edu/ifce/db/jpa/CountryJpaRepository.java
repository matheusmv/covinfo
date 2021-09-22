package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryJpaRepository extends JpaRepository<Country, Long> {

}
