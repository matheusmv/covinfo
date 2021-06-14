package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.userdtos.EmailDTO;
import br.edu.ifce.backend.adpters.dto.userdtos.PasswordDTO;
import br.edu.ifce.backend.adpters.dto.userdtos.UserRegistrationDTO;
import br.edu.ifce.backend.domain.ports.driver.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final RegisterAUser registerAUser;
    private final ConfirmNewUserAccount confirmNewUserAccount;
    private final ResendAccountConfirmationEmail resendAccountConfirmationEmail;
    private final SendLinkToChangePassword sendLinkToChangePassword;
    private final ResetUserPassword resetUserPassword;

    @PostMapping("/registration")
    public ResponseEntity<Void> registerAUser(@RequestBody UserRegistrationDTO request) {
        registerAUser.execute(request.toUserWithAddress());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/confirmation")
    public ResponseEntity<String> confirmNewUserAccount(@RequestParam("token") String token) {
        var message = confirmNewUserAccount.execute(token);

        return ResponseEntity.ok().body(message);
    }

    @PostMapping("/resend-confirmation")
    public ResponseEntity<String> resendEmailConfirmation(@RequestBody EmailDTO request) {
        var message = resendAccountConfirmationEmail.execute(request.getEmail());

        return ResponseEntity.ok().body(message);
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> sendLinkToChangePassword(@RequestBody EmailDTO request) {
        sendLinkToChangePassword.execute(request.getEmail());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reset")
    public ResponseEntity<Void> resetUserPassword(@RequestParam("token") String token, @RequestBody PasswordDTO request) {
        resetUserPassword.execute(token, request.getPassword());

        return ResponseEntity.noContent().build();
    }
}
