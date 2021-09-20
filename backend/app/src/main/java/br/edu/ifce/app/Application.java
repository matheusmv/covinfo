package br.edu.ifce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EntityScan(basePackages = "br.edu.ifce.domain")
public class Application {

    // TODO: criar testes

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}