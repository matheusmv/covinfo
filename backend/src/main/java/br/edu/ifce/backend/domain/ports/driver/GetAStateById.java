package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.State;

public interface GetAStateById {
    State execute(Long id);
}
