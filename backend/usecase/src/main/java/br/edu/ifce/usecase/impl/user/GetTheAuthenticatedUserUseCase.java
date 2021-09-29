package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.usecase.ports.driven.AddressRepository;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.GetTheAuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetTheAuthenticatedUserUseCase implements GetTheAuthenticatedUser {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserAuthenticationService userAuthenticationService;

    @Override
    public User execute() {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser)) {
            throw new AuthorizationException("Access denied.");
        }

        return userRepository.find(authUser.getEmail())
                .map(user -> {
                    var userAddress = addressRepository.find(user.getId()).orElse(null);

                    user.setAddress(userAddress);

                    return user;
                })
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User with email %s not found", authUser.getEmail())));
    }
}
