package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.citydtos.CityDTO;
import br.edu.ifce.backend.adpters.dto.citydtos.CityWithStateDTO;
import br.edu.ifce.backend.domain.ports.driver.GetACityById;
import br.edu.ifce.backend.domain.ports.driver.GetAllCities;
import br.edu.ifce.backend.domain.ports.driver.SearchACityByName;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cities")
@AllArgsConstructor
public class CityController {

    private final GetAllCities getAllCities;
    private final GetACityById getACityById;
    private final SearchACityByName searchACityByName;

    @GetMapping
    public ResponseEntity<Page<CityDTO>> getAllCities(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

        var listOfCities = getAllCities.execute(page, linesPerPage, direction, orderBy)
                .map(CityDTO::new);

        return ResponseEntity.ok().body(listOfCities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityWithStateDTO> getACityById(@PathVariable Long id) {
        var city = getACityById.execute(id);

        return ResponseEntity.ok().body(new CityWithStateDTO(city));
    }

    @GetMapping("/name/{cityName}")
    public ResponseEntity<List<CityDTO>> searchACityByName(@PathVariable String cityName) {
        var listOfCities = searchACityByName.execute(cityName)
                .stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listOfCities);
    }
}
