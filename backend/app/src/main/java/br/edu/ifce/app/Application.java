package br.edu.ifce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = "br.edu.ifce.**")
public class Application {

    // TODO: criar testes

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}