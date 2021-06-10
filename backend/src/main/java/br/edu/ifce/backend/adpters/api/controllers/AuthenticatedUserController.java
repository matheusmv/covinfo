package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.messagedtos.MessageDTO;
import br.edu.ifce.backend.adpters.dto.messagedtos.MessageWithContentDTO;
import br.edu.ifce.backend.adpters.dto.userdtos.UserDTO;
import br.edu.ifce.backend.domain.ports.driver.GetAMessageById;
import br.edu.ifce.backend.domain.ports.driver.GetAllMessagesFromAuthenticatedUser;
import br.edu.ifce.backend.domain.ports.driver.GetTheAuthenticatedUser;
import br.edu.ifce.backend.domain.ports.driver.RefreshUserAuthToken;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class AuthenticatedUserController {

    private final GetTheAuthenticatedUser getTheAuthenticatedUser;
    private final RefreshUserAuthToken refreshUserAuthToken;
    private final GetAllMessagesFromAuthenticatedUser getAllMessagesFromAuthenticatedUser;
    private final GetAMessageById getAMessageById;

    @GetMapping
    public ResponseEntity<UserDTO> getTheAuthenticatedUser() {
        var user = getTheAuthenticatedUser.execute();

        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshUserAuthToken(HttpServletResponse response) {
        var token = refreshUserAuthToken.execute();

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/messages")
    public ResponseEntity<Page<MessageDTO>> getAllMessagesFromAuthenticatedUser(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "createdAt") String orderBy) {

        var listOfMessages = getAllMessagesFromAuthenticatedUser.execute(page, linesPerPage, direction, orderBy)
                .map(MessageDTO::new);

        return ResponseEntity.ok().body(listOfMessages);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<MessageWithContentDTO> getAMessageById(@PathVariable Long id) {
        var message = getAMessageById.execute(id);

        return ResponseEntity.ok().body(new MessageWithContentDTO(message));
    }
}
