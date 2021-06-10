package br.edu.ifce.backend.adpters.api.controllers.management;

import br.edu.ifce.backend.adpters.dto.messagedtos.MessageDTO;
import br.edu.ifce.backend.domain.ports.driver.GetAllMessages;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/management/api/v1/messages")
@AllArgsConstructor
public class MessageManagementController {

    private final GetAllMessages getAllMessages;

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAllMessages() {
        var listOfMessages = getAllMessages.execute()
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listOfMessages);
    }
}
