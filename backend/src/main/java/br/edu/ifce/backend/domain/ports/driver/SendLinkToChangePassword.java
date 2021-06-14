package br.edu.ifce.backend.domain.ports.driver;

public interface SendLinkToChangePassword {
    void execute(String email);
}
