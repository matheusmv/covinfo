package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.State;

import java.util.List;

public interface StateRepository {
    void create(State state);

    List<State> listAll();

    State findById(Long id);

    void update(Long id, State state);

    void delete(Long id);
}
