package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.City;
import br.edu.ifce.backend.domain.ports.driven.CityRepository;
import br.edu.ifce.backend.domain.ports.driver.GetACityById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetACityByIdUseCase implements GetACityById {

    private final CityRepository cityRepository;

    @Override
    public City execute(Long id) {
        return cityRepository.findById(id);
    }
}
