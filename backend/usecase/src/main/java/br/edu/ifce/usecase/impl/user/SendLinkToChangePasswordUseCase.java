package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.domain.PasswordToken;
import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.exceptions.InvalidEmailException;
import br.edu.ifce.usecase.ports.driven.EmailService;
import br.edu.ifce.usecase.ports.driven.PasswordTokenRepository;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.SendLinkToChangePassword;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SendLinkToChangePasswordUseCase implements SendLinkToChangePassword {

    private final UserRepository userRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public void execute(String email) {
        var user = userRepository.find(email)
                .orElseThrow(() -> new InvalidEmailException(
                        String.format("%s with email %s not found.", User.class.getSimpleName(), email)));

        var passwordToken = passwordTokenRepository.find(user.getId()).orElse(null);

        if (Objects.nonNull(passwordToken) && passwordToken.hasExpired()) {
            passwordToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            passwordTokenRepository.update(passwordToken);
        }

        if (Objects.isNull(passwordToken)) {
            var token = RandomStringUtils.random(10, true, true);
            passwordToken = newPasswordToken(token, user);
            passwordTokenRepository.create(passwordToken);
        }

        emailService.sendNewPasswordEmail(user, passwordToken.getToken());
    }

    private PasswordToken newPasswordToken(String token, User user) {
        return new PasswordToken(
                user.getId(),
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
    }
}
