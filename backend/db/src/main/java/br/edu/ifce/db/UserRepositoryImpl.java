package br.edu.ifce.db;

import br.edu.ifce.usecase.exceptions.DataIntegrityException;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.db.jpa.UserJpaRepository;
import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public Page<User> listAll(Pageable pageable) {
        return userJpaRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with id %d not found.", User.class.getSimpleName(), id)));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public Boolean emailIsAlreadyInUse(String email) {
        return userJpaRepository.findByEmail(email).isPresent();
    }

    @Override
    public void update(Long id, User user) {
        findById(id);
        userJpaRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            userJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete because there are associated entities.");
        }
    }
}