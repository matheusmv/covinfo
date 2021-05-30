package br.edu.ifce.backend.adpters.db.jpa;

import br.edu.ifce.backend.domain.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateJpaRepository extends JpaRepository<State, Long> {

}
