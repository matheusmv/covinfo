package br.edu.ifce.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BackendApplication {

    // TODO: mover value objects para dto
    // TODO: organizar use cases
    // TODO: criar testes

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
