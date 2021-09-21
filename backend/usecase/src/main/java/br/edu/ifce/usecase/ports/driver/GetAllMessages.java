package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.domain.Message;

import java.util.List;

public interface GetAllMessages {
    List<Message> execute();
}
