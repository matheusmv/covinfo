package br.edu.ifce.domain.ports.driver;

public interface ConfirmNewUserAccount {
    String execute(String confirmationToken);
}
