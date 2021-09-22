package br.edu.ifce.usecase.ports.driven;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

public interface UserAuthenticationService {
    AuthenticatedUser getAuthenticatedUser();

    String refreshAuthToken(String email);

    @Data
    @AllArgsConstructor
    class AuthenticatedUser {
        private String email;
        private Boolean isAuthenticated;
        private Set<String> roles;
    }
}
