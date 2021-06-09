package br.edu.ifce.backend.adpters.db.jpa;

import br.edu.ifce.backend.domain.entities.Message;
import br.edu.ifce.backend.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MessageJpaRepository extends JpaRepository<Message, Long> {

    @Transactional(readOnly = true)
    Page<Message> findByUser(User user, Pageable pageRequest);
}
