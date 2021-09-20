package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.entities.Message;

import java.util.List;

public interface GetAllMessages {
    List<Message> execute();
}
