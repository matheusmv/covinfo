package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.citydtos.CityDTO;
import br.edu.ifce.backend.adpters.dto.citydtos.CityWithStateDTO;
import br.edu.ifce.backend.domain.ports.driver.GetACityById;
import br.edu.ifce.backend.domain.ports.driver.GetAllCities;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cities")
@AllArgsConstructor
public class CityController {

    private final GetAllCities getAllCities;
    private final GetACityById getACityById;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        var listOfCities = getAllCities.execute()
                .stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listOfCities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityWithStateDTO> getACityById(@PathVariable Long id) {
        var city = getACityById.execute(id);

        return ResponseEntity.ok().body(new CityWithStateDTO(city));
    }
}
