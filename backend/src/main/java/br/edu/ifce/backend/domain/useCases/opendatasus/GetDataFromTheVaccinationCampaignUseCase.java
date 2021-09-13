package br.edu.ifce.backend.domain.useCases.opendatasus;

import br.edu.ifce.backend.domain.exceptions.AuthorizationException;
import br.edu.ifce.backend.domain.ports.driven.AddressRepository;
import br.edu.ifce.backend.domain.ports.driven.OpendatasusVacinaConsumer;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driver.GetDataFromTheVaccinationCampaign;
import br.edu.ifce.backend.domain.ports.dto.VaccinationData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetDataFromTheVaccinationCampaignUseCase implements GetDataFromTheVaccinationCampaign {

    private final UserAuthenticationService userAuthenticationService;
    private final AddressRepository addressRepository;
    private final OpendatasusVacinaConsumer opendatasusVacinaConsumer;

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

        var vaccinationRates = opendatasusVacinaConsumer.obtainVaccinationRates(stateAcronym, cityName);

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