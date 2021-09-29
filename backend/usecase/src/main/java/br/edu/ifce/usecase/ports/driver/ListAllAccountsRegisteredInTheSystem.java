package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.domain.Page;
import br.edu.ifce.domain.User;

public interface ListAllAccountsRegisteredInTheSystem {
    Page<User> execute(Integer page, Integer linesPerPage);
}
