package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.ConfirmationToken;

import java.util.List;

public interface ConfirmationTokenRepository {
    void create(ConfirmationToken confirmationToken);

    List<ConfirmationToken> listAll();

    ConfirmationToken findById(Long id);

    void update(Long id, ConfirmationToken confirmationToken);

    void delete(Long id);
}
