package br.edu.ifce.app.adpters.db;

import br.edu.ifce.app.adpters.db.exceptions.DataIntegrityException;
import br.edu.ifce.app.adpters.db.exceptions.ObjectNotFoundException;
import br.edu.ifce.app.adpters.db.jpa.CountryJpaRepository;
import br.edu.ifce.domain.entities.Country;
import br.edu.ifce.domain.ports.driven.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    private final CountryJpaRepository countryJpaRepository;

    @Override
    public void create(Country country) {
        countryJpaRepository.save(country);
    }

    @Override
    public List<Country> listAll() {
        return countryJpaRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryJpaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with id %d not found.", Country.class.getSimpleName(), id)));
    }

    @Override
    public void update(Long id, Country country) {
        findById(id);
        countryJpaRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            countryJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete because there are associated entities.");
        }
    }
}
