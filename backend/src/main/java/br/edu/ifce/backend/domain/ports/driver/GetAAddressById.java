package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.Address;

public interface GetAAddressById {
    Address execute(Long id);
}
