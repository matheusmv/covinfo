package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.PasswordToken;
import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.exceptions.InvalidEmailException;
import br.edu.ifce.backend.domain.ports.driven.EmailService;
import br.edu.ifce.backend.domain.ports.driven.PasswordTokenRepository;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.SendLinkToChangePassword;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SendLinkToChangePasswordUseCase implements SendLinkToChangePassword {

    private final UserRepository userRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final EmailService emailService;

    @Transactional
    @Override
    public void execute(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidEmailException(
                        String.format("%s with email %s not found.", User.class.getSimpleName(), email)));

        var passwordToken = passwordTokenRepository.findById(user.getId());

        if (Objects.nonNull(passwordToken) && passwordToken.hasExpired()) {
            passwordToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            passwordTokenRepository.save(passwordToken);
        }

        if (Objects.isNull(passwordToken)) {
            var token = RandomStringUtils.random(10, true, true);
            passwordToken = newPasswordToken(token, user);
            passwordTokenRepository.save(passwordToken);
        }

        emailService.sendNewPasswordEmail(user, passwordToken.getToken());
    }

    private PasswordToken newPasswordToken(String token, User user) {
        return new PasswordToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
    }
}
