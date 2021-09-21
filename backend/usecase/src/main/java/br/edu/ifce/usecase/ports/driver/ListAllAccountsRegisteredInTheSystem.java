package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.domain.User;
import org.springframework.data.domain.Page;

public interface ListAllAccountsRegisteredInTheSystem {
    Page<User> execute(Integer page, Integer linesPerPage, String direction, String orderBy);
}
