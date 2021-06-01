package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Address;
import br.edu.ifce.backend.domain.ports.driven.AddressRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAllAddresses;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllAddressesUseCase implements GetAllAddresses {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> execute() {
        return addressRepository.listAll();
    }
}
