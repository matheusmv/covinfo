package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.domain.Address;

public interface UpdateAuthenticatedUserAddress {
    void execute(Address newAddress);
}
