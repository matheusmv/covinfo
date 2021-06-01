package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Address;
import br.edu.ifce.backend.domain.ports.driven.AddressRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAAddressById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAAddressByIdUseCase implements GetAAddressById {

    private final AddressRepository addressRepository;

    @Override
    public Address execute(Long id) {
        return addressRepository.findById(id);
    }
}
