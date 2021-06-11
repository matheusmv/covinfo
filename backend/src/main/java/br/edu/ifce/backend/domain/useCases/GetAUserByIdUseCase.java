package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAUserById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAUserByIdUseCase implements GetAUserById {

    private final UserRepository userRepository;
    private final UserAuthenticationService userAuthenticationService;

    @Override
    public User execute(Long id) {
        return userRepository.findById(id);
    }
}
