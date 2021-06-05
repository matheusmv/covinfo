package br.edu.ifce.backend.adpters.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

    @Async
    @Override
    public void sendEmail(SimpleMailMessage message) {
        mailSender.send(message);
    }
}
