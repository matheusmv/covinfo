package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.State;

import java.util.List;

public interface GetAllStates {
    List<State> execute();
}
