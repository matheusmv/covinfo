package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.messagedtos.MessageWithContentDTO;
import br.edu.ifce.backend.domain.ports.driver.GetAMessageById;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@AllArgsConstructor
public class MessageController {

    private final GetAMessageById getAMessageById;

    @GetMapping("/{id}")
    public ResponseEntity<MessageWithContentDTO> getAMessageById(@PathVariable Long id) {
        var message = getAMessageById.execute(id);

        return ResponseEntity.ok().body(new MessageWithContentDTO(message));
    }
}
