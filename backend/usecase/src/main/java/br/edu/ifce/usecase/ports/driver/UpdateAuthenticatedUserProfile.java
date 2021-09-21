package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.domain.User;

public interface UpdateAuthenticatedUserProfile {
    void execute(User newUser);
}
