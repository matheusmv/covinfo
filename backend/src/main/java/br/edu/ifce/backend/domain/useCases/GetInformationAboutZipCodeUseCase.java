package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.exceptions.InvalidZipException;
import br.edu.ifce.backend.domain.ports.driven.CityRepository;
import br.edu.ifce.backend.domain.ports.driven.PostmonConsumer;
import br.edu.ifce.backend.domain.ports.driver.GetInformationAboutZipCode;
import br.edu.ifce.backend.domain.valueObjects.CompleteZipCodeInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetInformationAboutZipCodeUseCase implements GetInformationAboutZipCode {

    private final PostmonConsumer postmonConsumer;
    private final CityRepository cityRepository;

    @Override
    public CompleteZipCodeInformation execute(String zip) {
        if (!zipIsValid(zip)) {
            throw new InvalidZipException(String.format("The zip code %s does not have a valid format.", zip));
        }

        var zipInformation = postmonConsumer.getZipInformation(zip);

        var cityName = zipInformation.getCidade();

        var cityInformation = cityRepository.findByName(cityName);

        return new CompleteZipCodeInformation(
                zipInformation.getCep(),
                cityInformation
        );
    }

    private boolean zipIsValid(String zip) {
        return zip.matches("^[0-9]{5}[-]?[0-9]{3}$");
    }
}
