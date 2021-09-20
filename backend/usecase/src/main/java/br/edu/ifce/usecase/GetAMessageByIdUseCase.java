package br.edu.ifce.usecase;

import br.edu.ifce.domain.entities.Message;
import br.edu.ifce.domain.ports.driven.MessageRepository;
import br.edu.ifce.domain.ports.driver.GetAMessageById;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetAMessageByIdUseCase implements GetAMessageById {

    private final MessageRepository messageRepository;

    @Override
    public Message execute(Long id) {
        return messageRepository.findById(id);
    }
}
