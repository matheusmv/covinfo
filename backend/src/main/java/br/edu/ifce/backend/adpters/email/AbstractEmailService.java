package br.edu.ifce.backend.adpters.email;

import br.edu.ifce.backend.adpters.email.exceptions.EmailServiceException;
import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driven.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractEmailService.class);

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendUserAccountConfirmationEmail(User user, String confirmationToken) {
        try {
            MimeMessage message = prepareMimeMessageFromUser(user, confirmationToken);
            sendHtmlEmail(message);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new EmailServiceException("Failed to send email.");
        }
    }

    protected MimeMessage prepareMimeMessageFromUser(User user, String confirmationToken) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

        message.setTo(user.getEmail());
        message.setFrom(sender);
        message.setSubject("Confirm Your Account");
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setText(htmlFromTemplateUserAccountConfirmation(user, confirmationToken), true);

        return mimeMessage;
    }

    protected String htmlFromTemplateUserAccountConfirmation(User user, String confirmationToken) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("confirmationToken", confirmationToken);

        return templateEngine.process("email/userAccountConfirmation", context);
    }
}
