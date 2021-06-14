package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.exceptions.InvalidConfirmationTokenException;
import br.edu.ifce.backend.domain.exceptions.ValidationException;
import br.edu.ifce.backend.domain.ports.driven.PasswordTokenRepository;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.ResetUserPassword;
import br.edu.ifce.backend.domain.useCases.utils.UserValidation;
import br.edu.ifce.backend.domain.useCases.utils.UserValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ResetUserPasswordUseCase implements ResetUserPassword {

    private final PasswordTokenRepository passwordTokenRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void execute(String token, String newPassword) {
        var passwordToken = passwordTokenRepository.findByToken(token);

        if (passwordToken.hasExpired()) {
            throw new InvalidConfirmationTokenException("token expired");
        }

        var user = passwordToken.getUser();

        user.setPassword(newPassword);

        validateUserPassword(user);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        passwordTokenRepository.delete(passwordToken.getId());
    }

    private void validateUserPassword(User user) {
        UserValidation validation = UserValidation.passwordIsValid();

        UserValidationResult result = validation.apply(user);

        if (result != UserValidationResult.SUCCESS) {
            throw new ValidationException(result.getResult());
        }
    }
}
