package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.User;

import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendUserAccountConfirmationEmail(User user, String confirmationToken);

    void sendNewPasswordEmail(User user, String newPassword);

    void sendHtmlEmail(MimeMessage message);
}
