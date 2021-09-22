package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordTokenJpaRepository extends JpaRepository<PasswordToken, Long> {

    Optional<PasswordToken> findByToken(String token);
}
