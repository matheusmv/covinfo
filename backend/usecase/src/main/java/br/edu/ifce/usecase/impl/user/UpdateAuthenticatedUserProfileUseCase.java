package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.usecase.exceptions.ValidationException;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.UpdateAuthenticatedUserProfile;
import br.edu.ifce.usecase.utils.UserValidation;
import br.edu.ifce.usecase.utils.UserValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateAuthenticatedUserProfileUseCase implements UpdateAuthenticatedUserProfile {

    private final UserAuthenticationService userAuthenticationService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void execute(User newUser) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (Objects.isNull(authUser)) {
            throw new AuthorizationException("Access denied.");
        }

        var user = userRepository.find(authUser.getEmail())
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User with email %s not found", authUser.getEmail())));

        updateUser(user, newUser);

        validateUserData(user);

        userRepository.update(user);
    }

    private void updateUser(User user, User newUser) {
        boolean newName = Objects.nonNull(newUser.getFullName());
        boolean newEmail = Objects.nonNull(newUser.getEmail());
        boolean newPassword = Objects.nonNull(newUser.getPassword());

        if (newName) {
            user.setFullName(newUser.getFullName());
        }

        if (newEmail) {
            user.setEmail(newUser.getEmail());
        }

        if (newPassword) {
            validateNewPassword(newUser.getPassword());
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }
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

    private void validateNewPassword(String password) {
        boolean passwordIsValid = !password.isBlank() && password.length() > 8;

        if (!passwordIsValid) {
            throw new ValidationException(UserValidationResult.PASSWORD_NOT_VALID.getResult());
        }
    }
}
