package br.edu.ifce.backend.domain.useCases;

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
        var zipInformation = postmonConsumer.getZipInformation(zip);

        var cityName = zipInformation.getCidade();

        var cityInformation = cityRepository.findByName(cityName);

        return new CompleteZipCodeInformation(
                zipInformation.getCep(),
                cityInformation
        );
    }
}
