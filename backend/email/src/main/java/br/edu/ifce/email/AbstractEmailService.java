package br.edu.ifce.email;

import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.ports.driven.EmailService;
import br.edu.ifce.usecase.exceptions.EmailServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractEmailService.class);

    @Value("${default.sender}")
    private String sender;

    @Value("${default.confirmation-endpoint}")
    private String confirmationEndpoint;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    @Override
    public void sendUserAccountConfirmationEmail(User user, String confirmationToken) {
        try {
            MimeMessage message = prepareAccountConfirmationMimeMessageFromUser(user, confirmationToken);
            sendHtmlEmail(message);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new EmailServiceException("Failed to send email.");
        }
    }

    protected MimeMessage prepareAccountConfirmationMimeMessageFromUser(User user, String confirmationToken) throws MessagingException {
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
        context.setVariable("confirmationURL", confirmationEndpoint);

        return templateEngine.process("email/userAccountConfirmation", context);
    }

    @Async
    @Override
    public void sendNewPasswordEmail(User user, String newPassword) {
        try {
            MimeMessage message = preparePasswordResetMimeMessageFromUser(user, newPassword);
            sendHtmlEmail(message);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new EmailServiceException("Failed to send email.");
        }
    }

    protected MimeMessage preparePasswordResetMimeMessageFromUser(User user, String newPassword) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

        message.setTo(user.getEmail());
        message.setFrom(sender);
        message.setSubject("Password Change Request");
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setText(htmlFromTemplateUserNewPassword(user, newPassword), true);

        return mimeMessage;
    }

    protected String htmlFromTemplateUserNewPassword(User user, String newPassword) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("password", newPassword);

        return templateEngine.process("email/userNewPassword", context);
    }
}
