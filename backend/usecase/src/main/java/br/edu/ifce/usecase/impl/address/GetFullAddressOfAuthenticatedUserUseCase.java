package br.edu.ifce.usecase.impl.address;

import br.edu.ifce.domain.Address;
import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.ports.driven.AddressRepository;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driver.GetFullAddressOfAuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
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
