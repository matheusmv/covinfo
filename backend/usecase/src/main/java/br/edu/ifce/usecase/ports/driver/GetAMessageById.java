package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.domain.Message;

public interface GetAMessageById {
    Message execute(Long id);
}
