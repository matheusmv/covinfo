package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Address;
import br.edu.ifce.backend.domain.exceptions.AuthorizationException;
import br.edu.ifce.backend.domain.ports.driven.AddressRepository;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driver.GetFullAddressOfAuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class GetFullAddressOfAuthenticatedUserUseCase implements GetFullAddressOfAuthenticatedUser {

    private final UserAuthenticationService userAuthenticationService;
    private final AddressRepository addressRepository;

    @Override
    public Address execute() {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser)) {
            throw new AuthorizationException("Access denied.");
        }

        return addressRepository.findByUserId(authUser.getId());
    }
}