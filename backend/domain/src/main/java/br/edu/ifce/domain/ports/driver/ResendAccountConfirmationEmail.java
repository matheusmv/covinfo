package br.edu.ifce.domain.ports.driver;

public interface ResendAccountConfirmationEmail {
    String execute(String email);
}
