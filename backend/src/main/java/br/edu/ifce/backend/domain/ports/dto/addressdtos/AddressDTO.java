package br.edu.ifce.backend.domain.ports.dto.addressdtos;

import br.edu.ifce.backend.domain.entities.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String zip;
    private String neighborhood;
    private String street;
    private Long cityId;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.zip = address.getZip();
        this.neighborhood = address.getNeighborhood();
        this.street = address.getStreet();
        this.cityId = address.getCity().getId();
    }
}
