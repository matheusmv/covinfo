package br.edu.ifce.api.controllers;

import br.edu.ifce.api.docs.UserControllerDocs;
import br.edu.ifce.domain.ports.driver.ConfirmNewUserAccount;
import br.edu.ifce.domain.ports.driver.RegisterAUser;
import br.edu.ifce.domain.ports.driver.ResendAccountConfirmationEmail;
import br.edu.ifce.domain.ports.driver.ResetUserPassword;
import br.edu.ifce.domain.ports.driver.SendLinkToChangePassword;
import br.edu.ifce.domain.ports.dto.userdtos.EmailDTO;
import br.edu.ifce.domain.ports.dto.userdtos.PasswordDTO;
import br.edu.ifce.domain.ports.dto.userdtos.UserRegistrationDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController implements UserControllerDocs {

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
    public ResponseEntity<Void> resetUserPassword(@RequestParam("token") String token,
                                                  @RequestBody PasswordDTO request) {
        resetUserPassword.execute(token, request.getPassword());

        return ResponseEntity.noContent().build();
    }
}
