package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.Address;

import java.util.List;

public interface GetAllAddresses {
    List<Address> execute();
}
