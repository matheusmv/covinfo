package br.edu.ifce.db;

import br.edu.ifce.db.exceptions.DataIntegrityException;
import br.edu.ifce.domain.exceptions.ObjectNotFoundException;
import br.edu.ifce.db.jpa.PasswordTokenJpaRepository;
import br.edu.ifce.domain.entities.PasswordToken;
import br.edu.ifce.domain.ports.driven.PasswordTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PasswordTokenRepositoryImpl implements PasswordTokenRepository {

    private final PasswordTokenJpaRepository passwordTokenJpaRepository;

    @Override
    public void save(PasswordToken passwordToken) {
        passwordTokenJpaRepository.save(passwordToken);
    }

    @Override
    public List<PasswordToken> listAll() {
        return passwordTokenJpaRepository.findAll();
    }

    @Override
    public PasswordToken findById(Long id) {
        return passwordTokenJpaRepository.findById(id).orElse(null);
    }

    @Override
    public PasswordToken findByToken(String token) {
        return passwordTokenJpaRepository.findByToken(token)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("Token with value %s not found.", token)));
    }

    @Override
    public void update(Long id, PasswordToken passwordToken) {
        findById(id);
        passwordTokenJpaRepository.save(passwordToken);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            passwordTokenJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete because there are associated entities.");
        }
    }
}
