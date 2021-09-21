package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.ConfirmationToken;

import java.util.List;

public interface ConfirmationTokenRepository {
    void save(ConfirmationToken confirmationToken);

    List<ConfirmationToken> listAll();

    ConfirmationToken findById(Long id);

    ConfirmationToken findByToken(String token);

    void update(Long id, ConfirmationToken confirmationToken);

    void delete(Long id);
}
