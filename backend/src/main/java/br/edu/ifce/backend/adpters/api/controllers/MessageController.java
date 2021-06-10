package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.messagedtos.MessageDTO;
import br.edu.ifce.backend.adpters.dto.messagedtos.MessageWithContentDTO;
import br.edu.ifce.backend.domain.ports.driver.GetAMessageById;
import br.edu.ifce.backend.domain.ports.driver.GetAllMessagesFromAuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
@AllArgsConstructor
public class MessageController {

    private final GetAMessageById getAMessageById;
    private final GetAllMessagesFromAuthenticatedUser getAllMessagesFromAuthenticatedUser;

    @GetMapping("/{id}")
    public ResponseEntity<MessageWithContentDTO> getAMessageById(@PathVariable Long id) {
        var message = getAMessageById.execute(id);

        return ResponseEntity.ok().body(new MessageWithContentDTO(message));
    }

    @GetMapping("/user")
    public ResponseEntity<Page<MessageDTO>> getUserMessages(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "createdAt") String orderBy) {

        var listOfMessages = getAllMessagesFromAuthenticatedUser.execute(page, linesPerPage, direction, orderBy)
                .map(MessageDTO::new);

        return ResponseEntity.ok().body(listOfMessages);
    }
}
