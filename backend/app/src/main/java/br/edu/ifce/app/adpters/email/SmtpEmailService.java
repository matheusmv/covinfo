package br.edu.ifce.app.adpters.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendHtmlEmail(MimeMessage message) {
        javaMailSender.send(message);
    }
}
