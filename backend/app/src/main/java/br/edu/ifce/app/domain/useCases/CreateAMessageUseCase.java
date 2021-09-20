package br.edu.ifce.app.domain.useCases;

import br.edu.ifce.app.domain.useCases.utils.MessageValidation;
import br.edu.ifce.app.domain.useCases.utils.MessageValidationResult;
import br.edu.ifce.domain.entities.Message;
import br.edu.ifce.domain.exceptions.AuthorizationException;
import br.edu.ifce.domain.exceptions.ValidationException;
import br.edu.ifce.domain.ports.driven.MessageRepository;
import br.edu.ifce.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.domain.ports.driven.UserRepository;
import br.edu.ifce.domain.ports.driver.CreateAMessage;
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
