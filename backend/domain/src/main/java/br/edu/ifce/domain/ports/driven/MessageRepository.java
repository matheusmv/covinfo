package br.edu.ifce.domain.ports.driven;

import br.edu.ifce.domain.entities.Message;
import br.edu.ifce.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageRepository {
    void create(Message message);

    List<Message> listAll();

    Message findById(Long id);

    Page<Message> findByUser(User user, Pageable pageRequest);

    void update(Long id, Message message);

    void delete(Long id);
}
