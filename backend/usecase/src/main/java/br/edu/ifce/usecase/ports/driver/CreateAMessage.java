package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.domain.Message;

public interface CreateAMessage {
    void execute(Message message);
}
