package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.userdtos.UserDTO;
import br.edu.ifce.backend.adpters.dto.userdtos.UserRegistrationDTO;
import br.edu.ifce.backend.domain.ports.driver.RegisterAUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final RegisterAUser registerAUser;

    @PostMapping("/registration")
    public ResponseEntity<String> registerAUser(@RequestBody UserRegistrationDTO request) {
        var message = registerAUser.execute(request.toUserWithAddress());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/confirmation")
    public ResponseEntity<String> confirmANewUser(@RequestParam("token") String token) {
        return null;
    }

    @PostMapping("/resend-confirmation")
    public ResponseEntity<?> resendEmailConfirmation(@RequestBody UserDTO request) {
        return null;
    }
}
