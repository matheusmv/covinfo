package br.edu.ifce.usecase.impl;

import br.edu.ifce.domain.Message;
import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.exceptions.ValidationException;
import br.edu.ifce.usecase.ports.driven.MessageRepository;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.CreateAMessage;
import br.edu.ifce.usecase.utils.MessageValidation;
import br.edu.ifce.usecase.utils.MessageValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreateAMessageUseCase implements CreateAMessage {

    private final UserAuthenticationService userAuthenticationService;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public void execute(Message message) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser)) {
            throw new AuthorizationException("Access denied.");
        }

        validateMessageData(message);

        var user = userRepository.findById(authUser.getId());

        message.setUser(user);
        message.setCreatedAt(LocalDateTime.now());

        messageRepository.create(message);
    }

    private void validateMessageData(Message message) {
        MessageValidation validation = MessageValidation.titleIsValid()
                .and(MessageValidation.contentIsValid());

        MessageValidationResult result = validation.apply(message);

        if (result != MessageValidationResult.SUCCESS) {
            throw new ValidationException(result.getResult());
        }
    }
}
