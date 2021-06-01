package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.ConfirmationToken;

import java.util.List;

public interface GetAllConfirmationTokens {
    List<ConfirmationToken> execute();
}
