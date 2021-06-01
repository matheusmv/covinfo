package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.Address;

import java.util.List;

public interface AddressRepository {
    void create(Address address);

    List<Address> listAll();

    Address findById(Long id);

    void update(Long id, Address address);

    void delete(Long id);
}
