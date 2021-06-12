package br.edu.ifce.backend.adpters.dto.addressdtos;

import br.edu.ifce.backend.domain.entities.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAddressDTO {

    private String zip;
    private String neighborhood;
    private String street;

    public Address toAddress() {
        return new Address(null, zip, neighborhood, street, null, null);
    }
}