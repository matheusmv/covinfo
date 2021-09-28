package br.edu.ifce.mysql.repository;

import br.edu.ifce.domain.State;
import br.edu.ifce.mysql.Page;
import br.edu.ifce.mysql.PageRequest;

import java.util.Optional;

public interface StateRepository {

    State create(State state);

    State update(State state);

    Optional<State> find(Long id);

    Page<State> find(PageRequest page);

    void delete(State state);

    void delete(Long id);
}
