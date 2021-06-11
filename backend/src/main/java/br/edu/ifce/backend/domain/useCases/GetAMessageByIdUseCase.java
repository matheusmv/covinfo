package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Message;
import br.edu.ifce.backend.domain.ports.driven.MessageRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAMessageById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAMessageByIdUseCase implements GetAMessageById {

    private final MessageRepository messageRepository;

    @Override
    public Message execute(Long id) {
        return messageRepository.findById(id);
    }
}
