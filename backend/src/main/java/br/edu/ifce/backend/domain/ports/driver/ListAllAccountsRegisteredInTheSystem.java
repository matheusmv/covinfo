package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.User;
import org.springframework.data.domain.Page;

public interface ListAllAccountsRegisteredInTheSystem {
    Page<User> execute(Integer page, Integer linesPerPage, String direction, String orderBy);
}
