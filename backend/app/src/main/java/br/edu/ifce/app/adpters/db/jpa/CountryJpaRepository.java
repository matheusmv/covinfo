package br.edu.ifce.app.adpters.db.jpa;

import br.edu.ifce.domain.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryJpaRepository extends JpaRepository<Country, Long> {

}
