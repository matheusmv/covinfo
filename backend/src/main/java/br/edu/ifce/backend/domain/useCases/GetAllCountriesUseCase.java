package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Country;
import br.edu.ifce.backend.domain.ports.driven.CountryRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAllCountries;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllCountriesUseCase implements GetAllCountries {

    private final CountryRepository countryRepository;

    @Override
    public List<Country> execute() {
        return countryRepository.listAll();
    }
}
