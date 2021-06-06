package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.ConfirmationToken;
import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.entities.enums.UserRole;
import br.edu.ifce.backend.domain.ports.driven.EmailService;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.RegisterAUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegisterAUserUseCase implements RegisterAUser {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Transactional
    @Override
    public String execute(User user) {
        user.setRole(UserRole.USER);

        var token = UUID.randomUUID().toString();

        user.setConfirmationToken(createConfirmationToken(token, user));
        user.setCreatedAt(LocalDateTime.now());

        userRepository.create(user);
        emailService.sendUserAccountConfirmationEmail(user, token);

        return "Confirm your email";
    }

    private ConfirmationToken createConfirmationToken(String token, User user) {
        return new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
    }
}
