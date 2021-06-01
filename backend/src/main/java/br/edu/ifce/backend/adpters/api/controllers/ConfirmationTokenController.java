package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.confirmationtokendtos.ConfirmationTokenDTO;
import br.edu.ifce.backend.adpters.dto.confirmationtokendtos.ConfirmationTokenWithUserDTO;
import br.edu.ifce.backend.domain.ports.driver.GetAConfirmationTokenById;
import br.edu.ifce.backend.domain.ports.driver.GetAllConfirmationTokens;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/confirmations")
@AllArgsConstructor
public class ConfirmationTokenController {

    private final GetAllConfirmationTokens getAllConfirmationTokens;
    private final GetAConfirmationTokenById getAConfirmationTokenById;

    @GetMapping
    public ResponseEntity<List<ConfirmationTokenDTO>> getAllConfirmationTokens() {
        var listOfTokens = getAllConfirmationTokens.execute()
                .stream()
                .map(ConfirmationTokenDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listOfTokens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAConfirmationTokenById(@PathVariable Long id) {
        var token = getAConfirmationTokenById.execute(id);

        return ResponseEntity.ok().body(new ConfirmationTokenWithUserDTO(token));
    }
}
