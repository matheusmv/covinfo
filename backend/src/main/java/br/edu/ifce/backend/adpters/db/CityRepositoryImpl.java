package br.edu.ifce.backend.adpters.db;

import br.edu.ifce.backend.adpters.db.exceptions.DataIntegrityException;
import br.edu.ifce.backend.adpters.db.exceptions.ObjectNotFoundException;
import br.edu.ifce.backend.adpters.db.jpa.CityJpaRepository;
import br.edu.ifce.backend.domain.entities.City;
import br.edu.ifce.backend.domain.ports.driven.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityRepositoryImpl implements CityRepository {

    private final CityJpaRepository cityJpaRepository;

    @Override
    public void create(City city) {
        cityJpaRepository.save(city);
    }

    @Override
    public List<City> listAll() {
        return cityJpaRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityJpaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with id %d not found.", City.class.getSimpleName(), id)));
    }

    @Override
    public void update(Long id, City city) {
        findById(id);
        cityJpaRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            cityJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete because there are associated entities.");
        }
    }
}
