package br.edu.ifce.backend.domain.useCases.address;

import br.edu.ifce.backend.domain.exceptions.ValidationException;
import br.edu.ifce.backend.domain.ports.driven.CityRepository;
import br.edu.ifce.backend.domain.ports.driven.PostmonConsumer;
import br.edu.ifce.backend.domain.ports.driver.GetInformationAboutZipCode;
import br.edu.ifce.backend.domain.ports.dto.CompleteZipCodeInformation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetInformationAboutZipCodeUseCase implements GetInformationAboutZipCode {

    private final PostmonConsumer postmonConsumer;
    private final CityRepository cityRepository;

    @Override
    public CompleteZipCodeInformation execute(String zip) {
        if (!zipIsValid(zip)) {
            throw new ValidationException(String.format("The zip code %s does not have a valid format.", zip));
        }

        var zipInformation = postmonConsumer.getZipInformation(zip);

        var cityName = zipInformation.getCidade();
        var stateInitials = zipInformation.getEstado();

        var cityInformation = cityRepository.findByNameAndStateInitials(cityName, stateInitials);

        return new CompleteZipCodeInformation(
                zipInformation.getCep(),
                cityInformation
        );
    }

    private boolean zipIsValid(String zip) {
        return zip.matches("^[0-9]{5}[-]?[0-9]{3}$");
    }
}
