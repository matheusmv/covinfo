package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.userdtos.EmailDTO;
import br.edu.ifce.backend.domain.ports.driver.RefreshUserAuthToken;
import br.edu.ifce.backend.domain.ports.driver.ResetUserPassword;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final RefreshUserAuthToken refreshUserAuthToken;
    private final ResetUserPassword resetUserPassword;

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshUserAuthToken(HttpServletResponse response) {
        var token = refreshUserAuthToken.execute();
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> resetUserPassword(@RequestBody EmailDTO request) {
        resetUserPassword.execute(request.getEmail());
        return ResponseEntity.noContent().build();
    }
}
