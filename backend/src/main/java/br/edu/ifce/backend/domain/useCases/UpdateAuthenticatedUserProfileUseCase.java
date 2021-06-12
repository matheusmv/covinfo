package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.exceptions.AuthorizationException;
import br.edu.ifce.backend.domain.exceptions.ValidationException;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.UpdateAuthenticatedUserProfile;
import br.edu.ifce.backend.domain.useCases.utils.UserValidation;
import br.edu.ifce.backend.domain.useCases.utils.UserValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateAuthenticatedUserProfileUseCase implements UpdateAuthenticatedUserProfile {

    private final UserAuthenticationService userAuthenticationService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void execute(User newUser) {
        var authUser = userAuthenticationService.getAuthenticatedUser();

        if (authUser == null) {
            throw new AuthorizationException("Access denied.");
        }

        var user = userRepository.findById(authUser.getId());

        updateUser(user, newUser);

        validateUserData(user);

        userRepository.save(user);
    }

    private void updateUser(User user, User newUser) {
        boolean newName = Optional.ofNullable(newUser.getFullName()).isPresent();
        boolean newEmail = Optional.ofNullable(newUser.getEmail()).isPresent();
        boolean newPassword = Optional.ofNullable(newUser.getPassword()).isPresent();

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
