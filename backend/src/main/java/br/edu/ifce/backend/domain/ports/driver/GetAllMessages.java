package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.Message;

import java.util.List;

public interface GetAllMessages {
    List<Message> execute();
}
