package br.edu.ifce.app.adpters.db;

import br.edu.ifce.app.adpters.db.exceptions.DataIntegrityException;
import br.edu.ifce.app.adpters.db.exceptions.ObjectNotFoundException;
import br.edu.ifce.app.adpters.db.jpa.AddressJpaRepository;
import br.edu.ifce.domain.entities.Address;
import br.edu.ifce.domain.ports.driven.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressJpaRepository addressJpaRepository;

    @Override
    public void create(Address address) {
        addressJpaRepository.save(address);
    }

    @Override
    public List<Address> listAll() {
        return addressJpaRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return addressJpaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with id %d not found.", Address.class.getSimpleName(), id)));
    }

    @Override
    public Address findByUserId(Long id) {
        return addressJpaRepository.findByUserId(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("%s with owner id %d not found.", Address.class.getSimpleName(), id)));
    }

    @Override
    public void update(Long id, Address address) {
        findById(id);
        addressJpaRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            addressJpaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete because there are associated entities.");
        }
    }
}
