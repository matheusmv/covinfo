package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateJpaRepository extends JpaRepository<State, Long> {

}
