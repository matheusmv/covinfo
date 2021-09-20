package br.edu.ifce.app.adpters.db;

import br.edu.ifce.app.adpters.db.exceptions.DataIntegrityException;
import br.edu.ifce.app.adpters.db.exceptions.ObjectNotFoundException;
import br.edu.ifce.app.adpters.db.jpa.StateJpaRepository;
import br.edu.ifce.domain.entities.State;
import br.edu.ifce.domain.ports.driven.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StateRepositoryImpl implements StateRepository {

    private final StateJpaRepository stateJpaRepository;

    @Override
    public void create(State state) {
        stateJpaRepository.save(state);
    }

    @Override
    public List<State> listAll() {
        return stateJpaRepository.findAll();
    }

    @Override
    public State findById(Long id) {
        return stateJpaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with id %d not found.", State.class.getSimpleName(), id)));
    }

    @Override
    public void update(Long id, State state) {
        findById(id);
        stateJpaRepository.save(state);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            stateJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete because there are associated entities.");
        }
    }
}
