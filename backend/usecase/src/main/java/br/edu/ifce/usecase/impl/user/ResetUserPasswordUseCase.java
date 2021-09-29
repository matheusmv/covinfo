package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.exceptions.InvalidConfirmationTokenException;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.usecase.exceptions.ValidationException;
import br.edu.ifce.usecase.ports.driven.PasswordTokenRepository;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.ResetUserPassword;
import br.edu.ifce.usecase.utils.UserValidation;
import br.edu.ifce.usecase.utils.UserValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ResetUserPasswordUseCase implements ResetUserPassword {

    private final PasswordTokenRepository passwordTokenRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void execute(String token, String newPassword) {
        var passwordToken = passwordTokenRepository.find(token)
                .orElseThrow(() -> new ObjectNotFoundException("Invalid token"));

        if (passwordToken.hasExpired()) {
            throw new InvalidConfirmationTokenException("token expired");
        }

        var user = passwordToken.getUser();

        user.setPassword(newPassword);

        validateUserPassword(user);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.update(user);
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
