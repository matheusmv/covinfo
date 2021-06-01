package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.ConfirmationToken;

public interface GetAConfirmationTokenById {
    ConfirmationToken execute(Long id);
}
