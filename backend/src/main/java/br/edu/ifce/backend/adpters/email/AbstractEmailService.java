package br.edu.ifce.backend.adpters.email;

import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driven.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendUserAccountConfirmationEmail(User user) {
        SimpleMailMessage message = prepareSimpleMailMessageFromUser(user);
        sendEmail(message);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromUser(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setFrom(sender); //TODO add google email
        message.setSubject("Confirm your email: " + user.getFullName());
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setText("confirme seu email.");// TODO add html
        return message;
    }
}
