package br.edu.ifce.backend.adpters.dto.countrydtos;

import br.edu.ifce.backend.domain.entities.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDTO {
    private Long id;
    private String name;
    private String initials;

    public CountryDTO(Country country) {
        this.id = country.getId();
        this.name = country.getName();
        this.initials = country.getInitials();
    }
}