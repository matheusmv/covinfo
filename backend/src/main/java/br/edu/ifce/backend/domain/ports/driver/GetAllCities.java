package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.City;
import org.springframework.data.domain.Page;

public interface GetAllCities {
    Page<City> execute(Integer page, Integer linesPerPage, String direction, String orderBy);
}
