package br.edu.ifce.usecase.impl.address;

import br.edu.ifce.domain.Address;
import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.usecase.exceptions.ValidationException;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.GetInformationAboutZipCode;
import br.edu.ifce.usecase.ports.driver.UpdateAuthenticatedUserAddress;
import br.edu.ifce.usecase.utils.AddressValidation;
import br.edu.ifce.usecase.utils.AddressValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateAuthenticatedUserAddressUseCase implements UpdateAuthenticatedUserAddress {

    private final UserAuthenticationService userAuthenticationService;
    private final UserRepository userRepository;
    private final GetInformationAboutZipCode getInformationAboutZipCode;

    @Override
    public void execute(Address newAddress) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser)) {
            throw new AuthorizationException("Access denied.");
        }

        var user = userRepository.findByEmail(authUser.getEmail())
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User with email %s not found", authUser.getEmail())));

        var userAddress = user.getAddress();

        updateAddress(userAddress, newAddress);

        validateUserAddressData(userAddress);

        userRepository.save(user);
    }

    private void updateAddress(Address address, Address newAddress) {
        boolean newZip = Objects.nonNull(newAddress.getZip());
        boolean newNeighborhood = Objects.nonNull(newAddress.getNeighborhood());
        boolean newStreet = Objects.nonNull(newAddress.getStreet());

        if (newZip) {
            var completeZipCodeInformation = getInformationAboutZipCode.execute(newAddress.getZip());
            address.setZip(completeZipCodeInformation.getZip());
            address.setCity(completeZipCodeInformation.getCity());
        }

        if (newNeighborhood) {
            address.setNeighborhood(newAddress.getNeighborhood());
        }

        if (newStreet) {
            address.setStreet(newAddress.getStreet());
        }
    }

    private void validateUserAddressData(Address address) {
        AddressValidation validation = AddressValidation.zipIsValid()
                .and(AddressValidation.neighborhoodIsValid())
                .and(AddressValidation.streetIsValid());

        AddressValidationResult result = validation.apply(address);

        if (result != AddressValidationResult.SUCCESS) {
            throw new ValidationException(result.getResult());
        }
    }
}
