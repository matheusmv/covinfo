package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateJpaRepository extends JpaRepository<State, Long> {

}
