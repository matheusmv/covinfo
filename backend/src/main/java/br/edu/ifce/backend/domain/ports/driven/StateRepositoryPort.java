package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.State;

public interface StateRepositoryPort {
    void create(State state);
}
