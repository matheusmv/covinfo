package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendUserAccountConfirmationEmail(User user);

    void sendEmail(SimpleMailMessage message);
}
