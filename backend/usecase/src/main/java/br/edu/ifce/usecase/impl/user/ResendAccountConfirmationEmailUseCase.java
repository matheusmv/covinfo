package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.exceptions.InvalidEmailException;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.usecase.ports.driven.ConfirmationTokenRepository;
import br.edu.ifce.usecase.ports.driven.EmailService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.ResendAccountConfirmationEmail;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ResendAccountConfirmationEmailUseCase implements ResendAccountConfirmationEmail {

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public String execute(String email) {
        var user = userRepository.find(email)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with email %s not found.", User.class.getSimpleName(), email)));

        if (user.getEnabled()) {
            throw new InvalidEmailException("email already confirmed");
        }

        var token = confirmationTokenRepository.find(user.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Token not found"));

        if (token.hasExpired()) {
            token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            confirmationTokenRepository.update(token);
        }

        emailService.sendUserAccountConfirmationEmail(user, token.getToken());

        return "Confirm your email";
    }
}
