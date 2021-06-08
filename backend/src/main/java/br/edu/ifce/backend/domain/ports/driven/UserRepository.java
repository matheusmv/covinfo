package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    List<User> listAll();

    User findById(Long id);

    User findByEmail(String email);

    Boolean emailIsAlreadyInUse(String email);

    void update(Long id, User user);

    void delete(Long id);
}
