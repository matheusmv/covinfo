package br.edu.ifce.db;

import br.edu.ifce.db.exceptions.DataIntegrityException;
import br.edu.ifce.domain.exceptions.ObjectNotFoundException;
import br.edu.ifce.db.jpa.ConfirmationTokenJpaRepository;
import br.edu.ifce.domain.entities.ConfirmationToken;
import br.edu.ifce.domain.ports.driven.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConfirmationTokeRepositoryImpl implements ConfirmationTokenRepository {

    private final ConfirmationTokenJpaRepository confirmationTokenJpaRepository;

    @Override
    public void save(ConfirmationToken confirmationToken) {
        confirmationTokenJpaRepository.save(confirmationToken);
    }

    @Override
    public List<ConfirmationToken> listAll() {
        return confirmationTokenJpaRepository.findAll();
    }

    @Override
    public ConfirmationToken findById(Long id) {
        return confirmationTokenJpaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with id %d not found.", ConfirmationToken.class.getSimpleName(), id)));
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return confirmationTokenJpaRepository.findByToken(token)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with value %s not found.", ConfirmationToken.class.getSimpleName(), token)));
    }

    @Override
    public void update(Long id, ConfirmationToken confirmationToken) {
        findById(id);
        confirmationTokenJpaRepository.save(confirmationToken);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            confirmationTokenJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete because there are associated entities.");
        }
    }
}
