package br.edu.ifce.backend.adpters.db;

import br.edu.ifce.backend.adpters.db.exceptions.DataIntegrityException;
import br.edu.ifce.backend.adpters.db.exceptions.ObjectNotFoundException;
import br.edu.ifce.backend.adpters.db.jpa.MessageJpaRepository;
import br.edu.ifce.backend.domain.entities.Message;
import br.edu.ifce.backend.domain.ports.driven.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageJpaRepository messageJpaRepository;

    @Override
    public void create(Message message) {
        messageJpaRepository.save(message);
    }

    @Override
    public List<Message> listAll() {
        return messageJpaRepository.findAll();
    }

    @Override
    public Message findById(Long id) {
        return messageJpaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with id %d not found.", Message.class.getSimpleName(), id)));
    }

    @Override
    public void update(Long id, Message message) {
        findById(id);
        messageJpaRepository.save(message);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            messageJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete because there are associated entities.");
        }
    }
}