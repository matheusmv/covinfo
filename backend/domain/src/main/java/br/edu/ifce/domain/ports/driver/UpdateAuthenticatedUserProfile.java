package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.entities.User;

public interface UpdateAuthenticatedUserProfile {
    void execute(User newUser);
}
