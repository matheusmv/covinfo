package br.edu.ifce.mysql.repository;

import br.edu.ifce.domain.User;
import br.edu.ifce.mysql.Page;
import br.edu.ifce.mysql.PageRequest;

import java.util.Optional;

public interface UserRepository {

    User create(User user);

    User update(User user);

    Optional<User> find(Long id);

    Optional<User> find(String email);

    Page<User> find(PageRequest page);

    void delete(User user);

    void delete(Long id);
}
