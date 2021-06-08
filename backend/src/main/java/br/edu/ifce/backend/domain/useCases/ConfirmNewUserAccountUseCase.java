package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.exceptions.InvalidConfirmationTokenException;
import br.edu.ifce.backend.domain.ports.driven.ConfirmationTokenRepository;
import br.edu.ifce.backend.domain.ports.driver.ConfirmNewUserAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmNewUserAccountUseCase implements ConfirmNewUserAccount {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Transactional
    @Override
    public String execute(String confirmationToken) {
        var token = confirmationTokenRepository.findByToken(confirmationToken);

        var user = token.getUser();

        if (user.getEnabled()) {
            throw new InvalidConfirmationTokenException("email already confirmed");
        }

        if (token.hasExpired()) {
            throw new InvalidConfirmationTokenException("token expired");
        }

        user.setLocked(false);
        user.setEnabled(true);

        token.setConfirmedAt(LocalDateTime.now());

        confirmationTokenRepository.save(token);

        return "Confirmed";
    }
}
