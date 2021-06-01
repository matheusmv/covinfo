package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Message;
import br.edu.ifce.backend.domain.ports.driven.MessageRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAllMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllMessagesUseCase implements GetAllMessages {

    private final MessageRepository messageRepository;

    @Override
    public List<Message> execute() {
        return messageRepository.listAll();
    }
}
