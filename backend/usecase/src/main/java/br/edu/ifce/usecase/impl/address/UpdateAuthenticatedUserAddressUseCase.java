package br.edu.ifce.usecase.impl.address;

import br.edu.ifce.domain.Address;
import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.usecase.exceptions.ValidationException;
import br.edu.ifce.usecase.ports.driven.AddressRepository;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.GetInformationAboutZipCode;
import br.edu.ifce.usecase.ports.driver.UpdateAuthenticatedUserAddress;
import br.edu.ifce.usecase.utils.AddressValidation;
import br.edu.ifce.usecase.utils.AddressValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateAuthenticatedUserAddressUseCase implements UpdateAuthenticatedUserAddress {

    private final UserAuthenticationService userAuthenticationService;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final GetInformationAboutZipCode getInformationAboutZipCode;

    @Override
    @Transactional
    public void execute(Address newAddress) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser)) {
            throw new AuthorizationException("Access denied.");
        }

        var userAddress = userRepository.find(authUser.getEmail())
                .map(User::getId)
                .map(addressRepository::find).orElseThrow(() -> new ObjectNotFoundException("Address not found"))
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User with email %s not found", authUser.getEmail())));

        updateAddress(userAddress, newAddress);

        validateUserAddressData(userAddress);

        addressRepository.update(userAddress);
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
