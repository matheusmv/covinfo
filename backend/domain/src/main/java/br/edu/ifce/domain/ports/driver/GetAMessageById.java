package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.entities.Message;

public interface GetAMessageById {
    Message execute(Long id);
}
