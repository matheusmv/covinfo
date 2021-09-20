package br.edu.ifce.app.adpters.env.config;

import br.edu.ifce.app.adpters.env.utils.CreateAdminAccount;
import br.edu.ifce.domain.ports.driven.EmailService;
import br.edu.ifce.email.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Value("${credentials.admin.email}")
    private String email;

    @Value("${credentials.admin.password}")
    private String password;

    @Autowired
    private CreateAdminAccount createAdminAccount;

    @Bean
    public boolean instantiateDataBase() {
        createAdminAccount.createAdminAccount(email, password);

        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
