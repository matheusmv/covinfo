package br.edu.ifce.usecase.ports.dto.addressdtos;

import br.edu.ifce.domain.Address;
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
