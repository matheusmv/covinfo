package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.Address;

import java.util.List;

public interface AddressRepository {
    void create(Address address);

    List<Address> listAll();

    Address findById(Long id);

    Address findByUserId(Long id);

    void update(Long id, Address address);

    void delete(Long id);
}
