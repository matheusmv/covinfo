package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.adpters.db.exceptions.ObjectNotFoundException;
import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driven.EmailService;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import br.edu.ifce.backend.domain.ports.driver.ResetUserPassword;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ResetUserPasswordUseCase implements ResetUserPassword {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    @Transactional
    @Override
    public void execute(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with email %s not found.", User.class.getSimpleName(), email)));

        var password = newRandomPassword();

        user.setPassword(bCryptPasswordEncoder.encode(password));

        userRepository.save(user);

        emailService.sendNewPasswordEmail(user, password);
    }

    private String newRandomPassword() {
        return RandomStringUtils.random(10, true, true);
    }
}
