package br.edu.ifce.usecase.impl.opendatasus;

import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.usecase.ports.driven.AddressRepository;
import br.edu.ifce.usecase.ports.driven.OpendatasusLeitosConsumer;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.ListTheMedicalCareUnitsInTheCity;
import br.edu.ifce.usecase.ports.responses.MedicalCareUnityInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ListTheMedicalCareUnitsInTheCityUseCase implements ListTheMedicalCareUnitsInTheCity {

    private final UserAuthenticationService userAuthenticationService;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OpendatasusLeitosConsumer opendatasusLeitosConsumer;

    @Override
    public MedicalCareUnityInfo execute() {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser)) {
            throw new AuthorizationException("Access denied.");
        }

        var userAddress = userRepository.find(authUser.getEmail())
                .map(User::getId)
                .map(addressRepository::find).orElseThrow(() -> new ObjectNotFoundException("Address not found"))
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User with email %s not found", authUser.getEmail())));

        var countryName = userAddress.getCity().getState().getCountry().getName();
        var stateName = userAddress.getCity().getState().getName();
        var cityName = userAddress.getCity().getName();

        var listOfMedialCareUnits = opendatasusLeitosConsumer.listMedicalCareUnits(stateName, cityName);

        return new MedicalCareUnityInfo(
                countryName,
                stateName,
                cityName,
                listOfMedialCareUnits
        );
    }
}
