package br.edu.ifce.backend.adpters.env.config;

import br.edu.ifce.backend.adpters.email.SmtpEmailService;
import br.edu.ifce.backend.domain.ports.driven.EmailService;
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
