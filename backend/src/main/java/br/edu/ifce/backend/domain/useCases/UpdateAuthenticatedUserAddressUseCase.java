package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Address;
import br.edu.ifce.backend.domain.exceptions.AuthorizationException;
import br.edu.ifce.backend.domain.exceptions.ValidationException;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.GetInformationAboutZipCode;
import br.edu.ifce.backend.domain.ports.driver.UpdateAuthenticatedUserAddress;
import br.edu.ifce.backend.domain.useCases.utils.AddressValidation;
import br.edu.ifce.backend.domain.useCases.utils.AddressValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateAuthenticatedUserAddressUseCase implements UpdateAuthenticatedUserAddress {

    private final UserAuthenticationService userAuthenticationService;
    private final UserRepository userRepository;
    private final GetInformationAboutZipCode getInformationAboutZipCode;

    @Override
    public void execute(Address newAddress) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (authUser == null) {
            throw new AuthorizationException("Access denied.");
        }

        var user = userRepository.findById(authUser.getId());

        var userAddress = user.getAddress();

        updateAddress(userAddress, newAddress);

        validateUserAddressData(userAddress);

        userRepository.save(user);
    }

    private void updateAddress(Address address, Address newAddress) {
        boolean newZip = Optional.ofNullable(newAddress.getZip()).isPresent();
        boolean newNeighborhood = Optional.ofNullable(newAddress.getNeighborhood()).isPresent();
        boolean newStreet = Optional.ofNullable(newAddress.getStreet()).isPresent();

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