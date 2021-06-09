package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.userdtos.EmailDTO;
import br.edu.ifce.backend.adpters.dto.userdtos.UserRegistrationDTO;
import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driver.ConfirmNewUserAccount;
import br.edu.ifce.backend.domain.ports.driver.GetAUserById;
import br.edu.ifce.backend.domain.ports.driver.RegisterAUser;
import br.edu.ifce.backend.domain.ports.driver.ResendAccountConfirmationEmail;
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
    private final GetAUserById getAUserById;

    @PostMapping("/registration")
    public ResponseEntity<String> registerAUser(@RequestBody UserRegistrationDTO request) {
        var message = registerAUser.execute(request.toUserWithAddress());

        return ResponseEntity.ok().body(message);
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

    @GetMapping("/{id}")
    public ResponseEntity<User> getAUserById(@PathVariable Long id) {
        var user = getAUserById.execute(id);
        return ResponseEntity.ok().body(user);
    }
}
