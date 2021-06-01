package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.State;
import br.edu.ifce.backend.domain.ports.driven.StateRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAStateById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAStateByIdUseCase implements GetAStateById {

    private final StateRepository stateRepository;

    @Override
    public State execute(Long id) {
        return stateRepository.findById(id);
    }
}
