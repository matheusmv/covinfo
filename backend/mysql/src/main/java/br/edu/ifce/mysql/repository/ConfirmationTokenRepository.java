package br.edu.ifce.mysql.repository;

import br.edu.ifce.domain.ConfirmationToken;
import br.edu.ifce.mysql.Page;
import br.edu.ifce.mysql.PageRequest;

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
