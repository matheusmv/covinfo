package br.edu.ifce.app.env.utils;

import br.edu.ifce.domain.User;
import br.edu.ifce.domain.enums.UserRole;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateAdminAccount {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void createAdminAccount(String email, String password) {
        boolean adminExists = userRepository.find(email).isPresent();

        if (!adminExists) {
            User admin = new User(null, "admin", email, passwordEncoder.encode(password));

            admin.setRole(UserRole.ADMIN);
            admin.setEnabled(true);
            admin.setLocked(false);
            admin.setCreatedAt(LocalDateTime.now());

            userRepository.create(admin);
        }
    }
}
