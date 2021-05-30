package br.edu.ifce.backend.adpters.db.jpa;

import br.edu.ifce.backend.domain.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageJpaRepository extends JpaRepository<Message, Long> {


}
