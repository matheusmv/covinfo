package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.entities.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordTokenJpaRepository extends JpaRepository<PasswordToken, Long> {
    Optional<PasswordToken> findByToken(String token);
}
