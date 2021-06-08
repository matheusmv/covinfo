package br.edu.ifce.backend.adpters.db;

import br.edu.ifce.backend.adpters.db.exceptions.DataIntegrityException;
import br.edu.ifce.backend.adpters.db.exceptions.ObjectNotFoundException;
import br.edu.ifce.backend.adpters.db.jpa.UserJpaRepository;
import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public List<User> listAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with id %d not found.", User.class.getSimpleName(), id)));
    }

    @Override
    public User findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with email %s not found.", User.class.getSimpleName(), email)));
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
