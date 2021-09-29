package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.domain.Page;
import br.edu.ifce.domain.PageRequest;
import br.edu.ifce.domain.User;
import br.edu.ifce.domain.enums.UserRole;
import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.ListAllAccountsRegisteredInTheSystem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ListAllAccountsRegisteredInTheSystemUseCase implements ListAllAccountsRegisteredInTheSystem {

    private final UserAuthenticationService userAuthenticationService;
    private final UserRepository userRepository;

    @Override
    public Page<User> execute(Integer page, Integer linesPerPage) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser) || !authUser.getRoles().contains(UserRole.ADMIN.getRole())) {
            throw new AuthorizationException("Access denied.");
        }

        return userRepository.find(new PageRequest(page, linesPerPage));
    }
}
