package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.State;
import br.edu.ifce.backend.domain.ports.driven.StateRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAllStates;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllStatesUseCase implements GetAllStates {

    private final StateRepository stateRepository;

    @Override
    public List<State> execute() {
        return stateRepository.listAll();
    }
}
