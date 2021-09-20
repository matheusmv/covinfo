package br.edu.ifce.domain.ports.driven;

import br.edu.ifce.domain.entities.User;

public interface UserAuthenticationService {
    User getAuthenticatedUser();

    String refreshAuthToken(String email);
}
