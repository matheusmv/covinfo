package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driver.RefreshUserAuthToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RefreshUserAuthTokenUseCase implements RefreshUserAuthToken {

    private final UserAuthenticationService userAuthenticationService;

    @Override
    public String execute() {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        return userAuthenticationService.refreshAuthToken(authUser.getUsername());
    }
}
