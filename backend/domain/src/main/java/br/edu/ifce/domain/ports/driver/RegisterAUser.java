package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.entities.User;

public interface RegisterAUser {
    void execute(User user);
}
