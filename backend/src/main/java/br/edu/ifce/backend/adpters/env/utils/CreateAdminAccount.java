package br.edu.ifce.backend.adpters.env.utils;

import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.entities.enums.UserRole;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
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
        boolean adminExists = userRepository.findByEmail(email).isPresent();

        if (!adminExists) {
            User admin = new User(null, "Admin", email, passwordEncoder.encode(password));

            admin.setRole(UserRole.ADMIN.getCode());
            admin.setEnabled(true);
            admin.setLocked(false);
            admin.setCreatedAt(LocalDateTime.now());

            userRepository.save(admin);
        }
    }
}
