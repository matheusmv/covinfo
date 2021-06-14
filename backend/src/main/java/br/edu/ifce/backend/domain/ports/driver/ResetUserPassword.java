package br.edu.ifce.backend.domain.ports.driver;

public interface ResetUserPassword {
    void execute(String token, String newPassword);
}
