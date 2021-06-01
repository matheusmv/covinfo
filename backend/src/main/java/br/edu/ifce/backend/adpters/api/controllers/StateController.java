package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.statedtos.StateDTO;
import br.edu.ifce.backend.adpters.dto.statedtos.StateWithCountryDTO;
import br.edu.ifce.backend.domain.ports.driver.GetAStateById;
import br.edu.ifce.backend.domain.ports.driver.GetAllStates;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/states")
@AllArgsConstructor
public class StateController {

    private final GetAllStates getAllStates;
    private final GetAStateById getAStateById;

    @GetMapping
    public ResponseEntity<List<StateDTO>> getAllStates() {
        var listOfStates = getAllStates.execute()
                .stream()
                .map(StateDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listOfStates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateWithCountryDTO> getAStateById(@PathVariable Long id) {
        var state = getAStateById.execute(id);

        return ResponseEntity.ok().body(new StateWithCountryDTO(state));
    }
}
