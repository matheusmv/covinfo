package br.edu.ifce.backend.domain.ports.driver;

public interface ConfirmNewUserAccount {
    String execute(String confirmationToken);
}
