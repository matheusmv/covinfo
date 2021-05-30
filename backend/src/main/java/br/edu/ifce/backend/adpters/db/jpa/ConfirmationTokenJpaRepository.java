package br.edu.ifce.backend.adpters.db.jpa;

import br.edu.ifce.backend.domain.entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenJpaRepository extends JpaRepository<ConfirmationToken, Long> {

}
