package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Message;
import br.edu.ifce.backend.domain.exceptions.AuthorizationException;
import br.edu.ifce.backend.domain.ports.driven.MessageRepository;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAllMessagesFromAuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAllMessagesFromAuthenticatedUserUseCase implements GetAllMessagesFromAuthenticatedUser {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final UserAuthenticationService userAuthenticationService;

    @Override
    public Page<Message> execute(Integer page, Integer linesPerPage, String direction, String orderBy) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (authUser == null) {
            throw new AuthorizationException("Access denied.");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        var user = userRepository.findById(authUser.getId());

        return messageRepository.findByUser(user, pageRequest);
    }
}
