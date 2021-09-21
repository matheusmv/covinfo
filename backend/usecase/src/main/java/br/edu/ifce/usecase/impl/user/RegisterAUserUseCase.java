package br.edu.ifce.usecase.impl.user;

import br.edu.ifce.domain.Address;
import br.edu.ifce.domain.City;
import br.edu.ifce.domain.ConfirmationToken;
import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.exceptions.InvalidEmailException;
import br.edu.ifce.usecase.exceptions.ValidationException;
import br.edu.ifce.usecase.ports.driven.EmailService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.usecase.ports.driver.GetInformationAboutZipCode;
import br.edu.ifce.usecase.ports.driver.RegisterAUser;
import br.edu.ifce.usecase.utils.AddressValidation;
import br.edu.ifce.usecase.utils.AddressValidationResult;
import br.edu.ifce.usecase.utils.UserValidation;
import br.edu.ifce.usecase.utils.UserValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterAUserUseCase implements RegisterAUser {

    private final UserRepository userRepository;
    private final GetInformationAboutZipCode getInformationAboutZipCode;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    @Override
    public void execute(User user) {
        validateUserData(user);

        validateUserAddressData(user.getAddress());

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

    private void validateUserAddressData(Address address) {
        AddressValidation validation = AddressValidation.zipIsValid()
                .and(AddressValidation.neighborhoodIsValid())
                .and(AddressValidation.streetIsValid());

        AddressValidationResult result = validation.apply(address);

        if (result != AddressValidationResult.SUCCESS) {
            throw new ValidationException(result.getResult());
        }

        address.setCity(getCityByZipCode(address.getZip()));
    }

    private City getCityByZipCode(String zip) {
        return getInformationAboutZipCode.execute(zip).getCity();
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
