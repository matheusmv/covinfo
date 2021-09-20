package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.entities.User;

public interface GetTheAuthenticatedUser {
    User execute();
}
