package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.domain.ports.driver.RefreshUserAuthToken;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final RefreshUserAuthToken refreshUserAuthToken;

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshUserAuthToken(HttpServletResponse response) {
        var token = refreshUserAuthToken.execute();
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
