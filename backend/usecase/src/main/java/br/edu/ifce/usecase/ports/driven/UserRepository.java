package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.Page;
import br.edu.ifce.domain.PageRequest;
import br.edu.ifce.domain.User;

import java.util.Optional;

public interface UserRepository {

    User create(User user);

    User update(User user);

    Optional<User> find(Long id);

    Optional<User> find(String email);

    Page<User> find(PageRequest page);

    void delete(User user);

    void delete(Long id);

    boolean emailIsAlreadyInUse(String email);
}
