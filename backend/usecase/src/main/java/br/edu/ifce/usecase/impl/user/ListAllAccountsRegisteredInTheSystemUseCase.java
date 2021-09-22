package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.domain.User;
import br.edu.ifce.domain.enums.UserRole;
import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.ListAllAccountsRegisteredInTheSystem;
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

        if (Objects.isNull(authUser) || !authUser.getRoles().contains(UserRole.ADMIN.getRole())) {
            throw new AuthorizationException("Access denied.");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return userRepository.listAll(pageRequest);
    }
}
