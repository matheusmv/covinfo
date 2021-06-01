package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Country;
import br.edu.ifce.backend.domain.ports.driven.CountryRepository;
import br.edu.ifce.backend.domain.ports.driver.GetACountryById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetACountryByIdUseCase implements GetACountryById {

    private final CountryRepository countryRepository;

    @Override
    public Country execute(Long id) {
        return countryRepository.findById(id);
    }
}
