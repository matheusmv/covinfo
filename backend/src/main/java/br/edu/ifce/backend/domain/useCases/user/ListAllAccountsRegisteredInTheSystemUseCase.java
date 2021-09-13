package br.edu.ifce.backend.domain.useCases.user;

import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.entities.enums.UserRole;
import br.edu.ifce.backend.domain.exceptions.AuthorizationException;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.ListAllAccountsRegisteredInTheSystem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ListAllAccountsRegisteredInTheSystemUseCase implements ListAllAccountsRegisteredInTheSystem {

    private final UserAuthenticationService userAuthenticationService;
    private final UserRepository userRepository;

    @Override
    public Page<User> execute(Integer page, Integer linesPerPage, String direction, String orderBy) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser) || !authUser.hasRole(UserRole.ADMIN)) {
            throw new AuthorizationException("Access denied.");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return userRepository.listAll(pageRequest);
    }
}
