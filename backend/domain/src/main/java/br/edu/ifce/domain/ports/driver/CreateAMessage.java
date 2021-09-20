package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.entities.Message;

public interface CreateAMessage {
    void execute(Message message);
}
