package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.domain.User;

public interface GetAUserById {
    User execute(Long id);
}
