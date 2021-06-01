package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.countrydtos.CountryDTO;
import br.edu.ifce.backend.domain.ports.driver.GetACountryById;
import br.edu.ifce.backend.domain.ports.driver.GetAllCountries;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/countries")
@AllArgsConstructor
public class CountryController {

    private final GetAllCountries getAllCountries;
    private final GetACountryById getACountryById;

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        var listOfCountries = getAllCountries.execute()
                .stream()
                .map(CountryDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listOfCountries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getACountryById(@PathVariable Long id) {
        var country = getACountryById.execute(id);

        return ResponseEntity.ok().body(new CountryDTO(country));
    }
}
