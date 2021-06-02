package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.City;
import br.edu.ifce.backend.domain.ports.driven.CityRepository;
import br.edu.ifce.backend.domain.ports.driver.GetACityByName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetACityByNameUseCase implements GetACityByName {

    private final CityRepository cityRepository;

    @Override
    public City execute(String name) {
        return cityRepository.findByName(name);
    }
}
