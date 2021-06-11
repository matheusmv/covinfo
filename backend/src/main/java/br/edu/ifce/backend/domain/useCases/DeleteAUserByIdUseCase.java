package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.DeleteAUserById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeleteAUserByIdUseCase implements DeleteAUserById {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void execute(Long id) {
        userRepository.delete(id);
    }
}
