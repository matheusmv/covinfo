package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.RegisterAUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegisterAUserUseCase implements RegisterAUser {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public String execute(User user) {
        userRepository.create(user);

        return "Confirm your email";
    }
}
