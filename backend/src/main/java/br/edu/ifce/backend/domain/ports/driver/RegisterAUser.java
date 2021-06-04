package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.User;

public interface RegisterAUser {
    String execute(User user);
}
