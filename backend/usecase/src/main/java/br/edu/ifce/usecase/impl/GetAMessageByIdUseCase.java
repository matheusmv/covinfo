package br.edu.ifce.usecase.impl;

import br.edu.ifce.domain.Message;
import br.edu.ifce.usecase.ports.driven.MessageRepository;
import br.edu.ifce.usecase.ports.driver.GetAMessageById;
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
