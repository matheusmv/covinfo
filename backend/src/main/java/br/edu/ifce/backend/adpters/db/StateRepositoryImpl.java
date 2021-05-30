package br.edu.ifce.backend.adpters.db;

import br.edu.ifce.backend.adpters.db.jpa.StateJpaRepository;
import br.edu.ifce.backend.domain.entities.State;
import br.edu.ifce.backend.domain.ports.driven.StateRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StateRepositoryImpl implements StateRepositoryPort {

    private final StateJpaRepository stateJpaRepository;

    @Override
    public void create(State state) {
        stateJpaRepository.save(state);
    }
}
