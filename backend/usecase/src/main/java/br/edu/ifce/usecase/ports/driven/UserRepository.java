package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Page<User> listAll(Pageable pageable);

    User findById(Long id);

    Optional<User> findByEmail(String email);

    Boolean emailIsAlreadyInUse(String email);

    void update(Long id, User user);

    void delete(Long id);
}