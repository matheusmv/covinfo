package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.PasswordToken;

import java.util.List;

public interface PasswordTokenRepository {
    void save(PasswordToken passwordToken);

    List<PasswordToken> listAll();

    PasswordToken findById(Long id);

    PasswordToken findByToken(String token);

    void update(Long id, PasswordToken passwordToken);

    void delete(Long id);
}
