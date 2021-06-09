package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.User;

import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendUserAccountConfirmationEmail(User user, String confirmationToken);

    void sendNewPasswordEmail(User user, String newPassword);

    void sendHtmlEmail(MimeMessage message);
}
