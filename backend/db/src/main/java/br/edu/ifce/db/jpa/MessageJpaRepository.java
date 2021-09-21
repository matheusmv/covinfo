package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.Message;
import br.edu.ifce.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MessageJpaRepository extends JpaRepository<Message, Long> {

    @Transactional(readOnly = true)
    Page<Message> findByUser(User user, Pageable pageRequest);
}
