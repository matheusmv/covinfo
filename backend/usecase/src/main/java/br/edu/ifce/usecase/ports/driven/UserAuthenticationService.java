package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.User;

public interface UserAuthenticationService {
    User getAuthenticatedUser();

    String refreshAuthToken(String email);
}
