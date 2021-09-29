package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.Page;
import br.edu.ifce.domain.PageRequest;
import br.edu.ifce.domain.PasswordToken;

import java.util.Optional;

public interface PasswordTokenRepository {

    PasswordToken create(PasswordToken passwordToken);

    PasswordToken update(PasswordToken passwordToken);

    Optional<PasswordToken> find(Long id);

    Optional<PasswordToken> find(String token);

    Page<PasswordToken> find(PageRequest page);

    void delete(PasswordToken passwordToken);

    void delete(Long id);
}
