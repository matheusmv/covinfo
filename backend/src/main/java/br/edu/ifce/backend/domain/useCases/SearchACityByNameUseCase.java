package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.City;
import br.edu.ifce.backend.domain.ports.driven.CityRepository;
import br.edu.ifce.backend.domain.ports.driver.SearchACityByName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchACityByNameUseCase implements SearchACityByName {

    private final CityRepository cityRepository;

    @Override
    public List<City> execute(String name) {
        return cityRepository.searchByName(name);
    }
}
