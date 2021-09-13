package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.exceptions.AuthorizationException;
import br.edu.ifce.backend.domain.ports.driven.AddressRepository;
import br.edu.ifce.backend.domain.ports.driven.OpendatasusLeitosConsumer;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driver.ListTheMedicalCareUnitsInTheCity;
import br.edu.ifce.backend.domain.ports.dto.MedicalCareUnityInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ListTheMedicalCareUnitsInTheCityUseCase implements ListTheMedicalCareUnitsInTheCity {

    private final UserAuthenticationService userAuthenticationService;
    private final AddressRepository addressRepository;
    private final OpendatasusLeitosConsumer opendatasusLeitosConsumer;

    @Override
    public MedicalCareUnityInfo execute() {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser)) {
            throw new AuthorizationException("Access denied.");
        }

        var userAddress = addressRepository.findByUserId(authUser.getId());

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
