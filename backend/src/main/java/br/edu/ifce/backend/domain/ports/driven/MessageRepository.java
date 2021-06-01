package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.Message;

import java.util.List;

public interface MessageRepository {
    void create(Message message);

    List<Message> listAll();

    Message findById(Long id);

    void update(Long id, Message message);

    void delete(Long id);
}
