package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.DeleteAUserById;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteAUserByIdUseCase implements DeleteAUserById {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void execute(Long id) {
        userRepository.delete(id);
    }
}
