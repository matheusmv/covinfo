package br.edu.ifce.mysql.repository;

import br.edu.ifce.domain.PasswordToken;
import br.edu.ifce.mysql.Page;
import br.edu.ifce.mysql.PageRequest;

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
