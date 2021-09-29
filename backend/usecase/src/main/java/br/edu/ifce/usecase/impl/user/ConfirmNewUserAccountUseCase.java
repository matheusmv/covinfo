package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.usecase.exceptions.InvalidConfirmationTokenException;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.usecase.ports.driven.ConfirmationTokenRepository;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.ConfirmNewUserAccount;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ConfirmNewUserAccountUseCase implements ConfirmNewUserAccount {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public String execute(String confirmationToken) {
        var token = confirmationTokenRepository.find(confirmationToken)
                .orElseThrow(() -> new ObjectNotFoundException("Invalid Token"));

        var user = userRepository.find(token.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Invalid Token"));

        if (Objects.nonNull(user) && user.getEnabled()) {
            throw new InvalidConfirmationTokenException("email already confirmed");
        }

        if (token.hasExpired()) {
            throw new InvalidConfirmationTokenException("token expired");
        }

        user.setLocked(false);
        user.setEnabled(true);

        token.setConfirmedAt(LocalDateTime.now());

        userRepository.update(user);
        confirmationTokenRepository.delete(token);

        return "Confirmed";
    }
}
