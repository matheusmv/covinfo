package br.edu.ifce.domain.ports.driven;

import br.edu.ifce.domain.entities.State;

import java.util.List;

public interface StateRepository {
    void create(State state);

    List<State> listAll();

    State findById(Long id);

    void update(Long id, State state);

    void delete(Long id);
}
