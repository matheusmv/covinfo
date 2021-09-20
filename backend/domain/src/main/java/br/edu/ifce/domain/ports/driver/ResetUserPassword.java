package br.edu.ifce.domain.ports.driver;

public interface ResetUserPassword {
    void execute(String token, String newPassword);
}
