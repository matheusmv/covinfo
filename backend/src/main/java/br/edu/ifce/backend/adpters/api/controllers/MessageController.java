package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.messagedtos.MessageDTO;
import br.edu.ifce.backend.adpters.dto.messagedtos.MessageWithContentDTO;
import br.edu.ifce.backend.domain.ports.driver.GetAMessageById;
import br.edu.ifce.backend.domain.ports.driver.GetAllMessages;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/messages")
@AllArgsConstructor
public class MessageController {

    private final GetAllMessages getAllMessages;
    private final GetAMessageById getAMessageById;

    @PreAuthorize("hasAnyRole('ADMIN', 'CONTENT_MANAGER')")
    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAllMessages() {
        var listOfMessages = getAllMessages.execute()
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listOfMessages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageWithContentDTO> getAMessageById(@PathVariable Long id) {
        var message = getAMessageById.execute(id);

        return ResponseEntity.ok().body(new MessageWithContentDTO(message));
    }
}
