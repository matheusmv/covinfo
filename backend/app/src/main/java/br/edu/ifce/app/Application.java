package br.edu.ifce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = "br.edu.ifce.**")
@EntityScan("br.edu.ifce.domain")
@EnableJpaRepositories("br.edu.ifce.db")
public class Application {

    // TODO: novo modulo consumers
    // TODO: novo modulo api
    // TODO: novo modulo security
    // TODO: criar testes

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}