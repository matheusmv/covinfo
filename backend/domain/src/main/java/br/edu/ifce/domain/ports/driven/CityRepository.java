package br.edu.ifce.domain.ports.driven;

import br.edu.ifce.domain.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CityRepository {
    void create(City city);

    Page<City> listAll(PageRequest pageRequest);

    City findById(Long id);

    City findByNameAndStateInitials(String name, String stateInitials);

    List<City> searchByName(String name);

    void update(Long id, City city);

    void delete(Long id);
}
