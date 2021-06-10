package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.adpters.db.exceptions.ObjectNotFoundException;
import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.exceptions.InvalidEmailException;
import br.edu.ifce.backend.domain.ports.driven.EmailService;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.ResendAccountConfirmationEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ResendAccountConfirmationEmailUseCase implements ResendAccountConfirmationEmail {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Transactional
    @Override
    public String execute(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with email %s not found.", User.class.getSimpleName(), email)));

        if (user.getEnabled()) {
            throw new InvalidEmailException("email already confirmed");
        }

        var token = user.getConfirmationToken();

        if (token.hasExpired()) {
            token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            userRepository.save(user);
        }

        emailService.sendUserAccountConfirmationEmail(user, token.getToken());

        return "Confirm your email";
    }
}
