package br.edu.ifce.usecase.ports.driver;

public interface ResetUserPassword {
    void execute(String token, String newPassword);
}
