package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.Message;

public interface GetAMessageById {
    Message execute(Long id);
}
