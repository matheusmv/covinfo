package br.edu.ifce.usecase.ports.driver;

public interface ConfirmNewUserAccount {
    String execute(String confirmationToken);
}
