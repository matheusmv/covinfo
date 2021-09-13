package br.edu.ifce.backend.domain.useCases.user;

import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAUserById;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetAUserByIdUseCase implements GetAUserById {

    private final UserRepository userRepository;

    @Override
    public User execute(Long id) {
        return userRepository.findById(id);
    }
}