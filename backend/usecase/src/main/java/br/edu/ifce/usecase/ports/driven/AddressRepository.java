package br.edu.ifce.usecase.ports.driven;

import br.edu.ifce.domain.Address;
import br.edu.ifce.domain.Page;
import br.edu.ifce.domain.PageRequest;

import java.util.Optional;

public interface AddressRepository {

    Address create(Address address);

    Address update(Address address);

    Optional<Address> find(Long id);

    Page<Address> find(PageRequest page);

    void delete(Address address);

    void delete(Long id);
}
