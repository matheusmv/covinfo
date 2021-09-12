package br.edu.ifce.backend.adpters.env.config;

import br.edu.ifce.backend.adpters.email.SmtpEmailService;
import br.edu.ifce.backend.adpters.env.utils.CreateAdminAccount;
import br.edu.ifce.backend.domain.ports.driven.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAutoValue;

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
