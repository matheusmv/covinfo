package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CityRepository {
    void create(City city);

    Page<City> listAll(PageRequest pageRequest);

    City findById(Long id);

    void update(Long id, City city);

    void delete(Long id);
}
