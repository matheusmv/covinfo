package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.entities.Address;

public interface UpdateAuthenticatedUserAddress {
    void execute(Address newAddress);
}
