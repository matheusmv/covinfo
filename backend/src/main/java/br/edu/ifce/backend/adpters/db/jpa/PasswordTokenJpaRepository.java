package br.edu.ifce.backend.adpters.db.jpa;

import br.edu.ifce.backend.domain.entities.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordTokenJpaRepository extends JpaRepository<PasswordToken, Long> {
    Optional<PasswordToken> findByToken(String token);
}
