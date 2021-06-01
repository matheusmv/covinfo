package br.edu.ifce.backend.adpters.dto.addressdtos;

import br.edu.ifce.backend.adpters.dto.citydtos.CityWithStateDTO;
import br.edu.ifce.backend.domain.entities.Address;
import br.edu.ifce.backend.domain.entities.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullAddressDTO {

    private Long id;
    private String zip;
    private String neighborhood;
    private String street;
    private CityWithStateDTO city;

    public FullAddressDTO(Address address) {
        this.id = address.getId();
        this.zip = address.getZip();
        this.neighborhood = address.getNeighborhood();
        this.street = address.getStreet();
        this.city = setCityWithStateDTO(address.getCity());
    }

    private CityWithStateDTO setCityWithStateDTO(City city) {
        return new CityWithStateDTO(city);
    }
}