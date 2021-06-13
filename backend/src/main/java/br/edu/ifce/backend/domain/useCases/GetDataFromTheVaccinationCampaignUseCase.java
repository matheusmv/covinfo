package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.exceptions.AuthorizationException;
import br.edu.ifce.backend.domain.ports.driven.AddressRepository;
import br.edu.ifce.backend.domain.ports.driven.OpendatasusConsumer;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driver.GetDataFromTheVaccinationCampaign;
import br.edu.ifce.backend.domain.valueObjects.VaccinationData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class GetDataFromTheVaccinationCampaignUseCase implements GetDataFromTheVaccinationCampaign {

    private final UserAuthenticationService userAuthenticationService;
    private final AddressRepository addressRepository;
    private final OpendatasusConsumer opendatasusConsumer;

    @Override
    public VaccinationData execute() {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser)) {
            throw new AuthorizationException("Access denied.");
        }

        var userAddress = addressRepository.findByUserId(authUser.getId());

        var countryName = userAddress.getCity().getState().getCountry().getName();
        var stateName = userAddress.getCity().getState().getName();
        var stateAcronym = userAddress.getCity().getState().getInitials();
        var cityName = userAddress.getCity().getName();

        var vaccinationRates = opendatasusConsumer.obtainVaccinationRates(stateAcronym, cityName);

        return new VaccinationData(
                countryName,
                stateName,
                cityName,
                vaccinationRates.getTotalInTheCountry(),
                vaccinationRates.getTotalInTheState(),
                vaccinationRates.getTotalInTheCity()
        );
    }
}
