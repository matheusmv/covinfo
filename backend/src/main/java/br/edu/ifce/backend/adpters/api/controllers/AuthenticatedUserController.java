package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.messagedtos.NewMessageDTO;
import br.edu.ifce.backend.adpters.dto.userdtos.UpdateUserDTO;
import br.edu.ifce.backend.adpters.dto.userdtos.UserDTO;
import br.edu.ifce.backend.domain.ports.driver.CreateAMessage;
import br.edu.ifce.backend.domain.ports.driver.GetTheAuthenticatedUser;
import br.edu.ifce.backend.domain.ports.driver.RefreshUserAuthToken;
import br.edu.ifce.backend.domain.ports.driver.UpdateAuthenticatedUserProfile;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class AuthenticatedUserController {

    private final GetTheAuthenticatedUser getTheAuthenticatedUser;
    private final RefreshUserAuthToken refreshUserAuthToken;
    private final CreateAMessage createAMessage;
    private final UpdateAuthenticatedUserProfile updateAuthenticatedUserProfile;

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

    @PostMapping("/messages")
    public ResponseEntity<Void> createAMessage(@RequestBody NewMessageDTO request) {
        createAMessage.execute(request.toMessage());

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateAuthenticatedUserProfile(@RequestBody UpdateUserDTO request) {
        updateAuthenticatedUserProfile.execute(request.toUser());

        return ResponseEntity.noContent().build();
    }
}
