package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Message;
import br.edu.ifce.backend.domain.entities.enums.UserRole;
import br.edu.ifce.backend.domain.exceptions.AuthorizationException;
import br.edu.ifce.backend.domain.ports.driven.MessageRepository;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driver.GetAMessageById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAMessageByIdUseCase implements GetAMessageById {

    private final MessageRepository messageRepository;
    private final UserAuthenticationService userAuthenticationService;

    @Override
    public Message execute(Long id) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (authUser == null) {
            throw new AuthorizationException("Access denied.");
        }

        var message = messageRepository.findById(id);
        var messageOwner = message.getUser();

        if (!authUser.hasRole(UserRole.ADMIN) && !messageOwner.getId().equals(authUser.getId())) {
            throw new AuthorizationException("Access denied.");
        }

        return message;
    }
}
