package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.adpters.security.user.UserSecurityService;

public interface UserAuthenticationService {
    UserSecurityService getAuthenticatedUser();

    String refreshAuthToken(String email);
}
