package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.City;
import br.edu.ifce.backend.domain.ports.driven.CityRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAllCities;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllCitiesUseCase implements GetAllCities {

    private final CityRepository cityRepository;

    @Override
    public List<City> execute() {
        return cityRepository.listAll();
    }
}
