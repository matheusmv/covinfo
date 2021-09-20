package br.edu.ifce.usecase;

import br.edu.ifce.domain.entities.Message;
import br.edu.ifce.domain.ports.driven.MessageRepository;
import br.edu.ifce.domain.ports.driver.GetAllMessages;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetAllMessagesUseCase implements GetAllMessages {

    private final MessageRepository messageRepository;

    @Override
    public List<Message> execute() {
        return messageRepository.listAll();
    }
}
