package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.ConfirmationToken;
import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.exceptions.InvalidEmailException;
import br.edu.ifce.backend.domain.exceptions.ValidationException;
import br.edu.ifce.backend.domain.ports.driven.EmailService;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.RegisterAUser;
import br.edu.ifce.backend.domain.useCases.utils.UserValidation;
import br.edu.ifce.backend.domain.useCases.utils.UserValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegisterAUserUseCase implements RegisterAUser {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    @Override
    public void execute(User user) {
        validateUserData(user);

        checkIfTheEmailIsAlreadyInUse(user.getEmail());

        var token = UUID.randomUUID().toString();

        user.setConfirmationToken(createConfirmationToken(token, user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
        emailService.sendUserAccountConfirmationEmail(user, token);
    }

    private void validateUserData(User user) {
        UserValidation validation = UserValidation.nameIsValid()
                .and(UserValidation.emailIsValid())
                .and(UserValidation.passwordIsValid());

        UserValidationResult result = validation.apply(user);

        if (result != UserValidationResult.SUCCESS) {
            throw new ValidationException(result.getResult());
        }
    }

    private void checkIfTheEmailIsAlreadyInUse(String email) {
        boolean user = userRepository.emailIsAlreadyInUse(email);

        if (user) {
            throw new InvalidEmailException("The email is already in use.");
        }
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
