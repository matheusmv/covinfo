package br.edu.ifce.usecase.utils;

import br.edu.ifce.domain.entities.User;

import java.util.Optional;
import java.util.function.Function;

import static br.edu.ifce.usecase.utils.UserValidationResult.EMAIL_NOT_VALID;
import static br.edu.ifce.usecase.utils.UserValidationResult.NAME_NOT_VALID;
import static br.edu.ifce.usecase.utils.UserValidationResult.PASSWORD_NOT_VALID;
import static br.edu.ifce.usecase.utils.UserValidationResult.SUCCESS;

public interface UserValidation extends Function<User, UserValidationResult> {
    static UserValidation nameIsValid() {
        return user -> {
            boolean nameNotNullAndNotEmpty = Optional.ofNullable(user)
                    .map(User::getFullName)
                    .filter(name -> !name.isBlank())
                    .isPresent();

            return nameNotNullAndNotEmpty ? SUCCESS : NAME_NOT_VALID;
        };
    }

    static UserValidation emailIsValid() {
        return user -> {
            boolean emailNotNullNotEmptyAndValidFormat = Optional.ofNullable(user)
                    .map(User::getEmail)
                    .filter(email -> !email.isBlank() && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
                    .isPresent();

            return emailNotNullNotEmptyAndValidFormat ? SUCCESS : EMAIL_NOT_VALID;
        };
    }

    static UserValidation passwordIsValid() {
        return user -> {
            boolean passwordNotNullNotEmptyAndHasGoodLength = Optional.ofNullable(user)
                    .map(User::getPassword)
                    .filter(password -> !password.isBlank() && password.length() > 8)
                    .isPresent();

            return passwordNotNullNotEmptyAndHasGoodLength ? SUCCESS : PASSWORD_NOT_VALID;
        };
    }

    default UserValidation and(UserValidation validation) {
        return user -> {
            UserValidationResult result = this.apply(user);
            return result.equals(SUCCESS) ? validation.apply(user) : result;
        };
    }
}
