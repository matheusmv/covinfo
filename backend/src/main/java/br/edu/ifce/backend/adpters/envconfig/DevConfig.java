package br.edu.ifce.backend.adpters.envconfig;

import br.edu.ifce.backend.adpters.utils.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private TestService testService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAutoValue;

    @Bean
    public boolean instantiateDataBase() {

        if (!"create".equals(ddlAutoValue)) {
            return false;
        }

        testService.InstantiateTestDataBase();
        return true;
    }
}
