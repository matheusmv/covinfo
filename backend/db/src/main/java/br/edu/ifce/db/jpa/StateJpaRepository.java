package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateJpaRepository extends JpaRepository<State, Long> {

}
