package br.edu.ifce.backend.domain.ports.driver;

public interface ResendAccountConfirmationEmail {
    String execute(String email);
}
