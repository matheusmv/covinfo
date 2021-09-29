package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.ConfirmationToken;
import br.edu.ifce.domain.Page;
import br.edu.ifce.domain.PageRequest;

import java.util.Optional;

public interface ConfirmationTokenRepository {

    ConfirmationToken create(ConfirmationToken confirmationToken);

    ConfirmationToken update(ConfirmationToken confirmationToken);

    Optional<ConfirmationToken> find(Long id);

    Optional<ConfirmationToken> find(String token);

    Page<ConfirmationToken> find(PageRequest page);

    void delete(ConfirmationToken confirmationToken);

    void delete(Long id);
}
