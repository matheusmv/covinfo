package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.Message;

public interface CreateAMessage {
    void execute(Message message);
}
