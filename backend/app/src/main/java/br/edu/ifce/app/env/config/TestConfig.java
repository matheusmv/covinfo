package br.edu.ifce.app.env.config;

import br.edu.ifce.usecase.ports.driven.EmailService;
import br.edu.ifce.email.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
