package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.Message;
import org.springframework.data.domain.Page;

public interface GetAllMessagesFromAuthenticatedUser {
    Page<Message> execute(Integer page, Integer linesPerPage, String direction, String orderBy);
}
