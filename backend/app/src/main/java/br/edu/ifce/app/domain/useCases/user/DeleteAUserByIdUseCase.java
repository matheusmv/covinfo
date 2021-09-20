package br.edu.ifce.app.domain.useCases.user;

import br.edu.ifce.domain.ports.driven.UserRepository;
import br.edu.ifce.domain.ports.driver.DeleteAUserById;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteAUserByIdUseCase implements DeleteAUserById {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void execute(Long id) {
        userRepository.delete(id);
    }
}
