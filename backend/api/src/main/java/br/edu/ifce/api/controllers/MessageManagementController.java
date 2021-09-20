package br.edu.ifce.api.controllers;

import br.edu.ifce.api.docs.MessageManagementControllerDocs;
import br.edu.ifce.domain.ports.driver.GetAMessageById;
import br.edu.ifce.domain.ports.driver.GetAllMessages;
import br.edu.ifce.domain.ports.dto.messagedtos.MessageDTO;
import br.edu.ifce.domain.ports.dto.messagedtos.MessageWithContentDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/management/api/v1/messages")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MessageManagementController implements MessageManagementControllerDocs {

    private final GetAllMessages getAllMessages;
    private final GetAMessageById getAMessageById;

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
