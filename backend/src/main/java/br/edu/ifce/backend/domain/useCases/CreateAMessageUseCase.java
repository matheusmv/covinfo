package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Message;
import br.edu.ifce.backend.domain.exceptions.AuthorizationException;
import br.edu.ifce.backend.domain.ports.driven.MessageRepository;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.CreateAMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateAMessageUseCase implements CreateAMessage {

    private final UserAuthenticationService userAuthenticationService;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public void execute(Message message) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (authUser == null) {
            throw new AuthorizationException("Access denied.");
        }

        var user = userRepository.findById(authUser.getId());

        message.setUser(user);
        message.setCreatedAt(LocalDateTime.now());

        messageRepository.create(message);
    }
}
