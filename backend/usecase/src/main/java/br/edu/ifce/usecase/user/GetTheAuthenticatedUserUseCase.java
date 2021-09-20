package br.edu.ifce.usecase.user;

import br.edu.ifce.domain.entities.User;
import br.edu.ifce.domain.exceptions.AuthorizationException;
import br.edu.ifce.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.domain.ports.driven.UserRepository;
import br.edu.ifce.domain.ports.driver.GetTheAuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetTheAuthenticatedUserUseCase implements GetTheAuthenticatedUser {

    private final UserRepository userRepository;
    private final UserAuthenticationService userAuthenticationService;

    @Override
    public User execute() {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser)) {
            throw new AuthorizationException("Access denied.");
        }

        return userRepository.findById(authUser.getId());
    }
}
