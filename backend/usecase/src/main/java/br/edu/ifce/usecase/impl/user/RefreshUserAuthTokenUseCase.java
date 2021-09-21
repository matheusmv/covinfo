package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driver.RefreshUserAuthToken;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RefreshUserAuthTokenUseCase implements RefreshUserAuthToken {

    private final UserAuthenticationService userAuthenticationService;

    @Override
    public String execute() {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        return userAuthenticationService.refreshAuthToken(authUser.getEmail());
    }
}
