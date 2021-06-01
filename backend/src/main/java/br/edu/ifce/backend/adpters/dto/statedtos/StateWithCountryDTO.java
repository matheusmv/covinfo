package br.edu.ifce.backend.adpters.dto.statedtos;

import br.edu.ifce.backend.adpters.dto.countrydtos.CountryDTO;
import br.edu.ifce.backend.domain.entities.Country;
import br.edu.ifce.backend.domain.entities.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateWithCountryDTO {

    private Long id;
    private String name;
    private String initials;
    private CountryDTO country;

    public StateWithCountryDTO(State state) {
        this.id = state.getId();
        this.name = state.getName();
        this.initials = state.getInitials();
        this.country = setCountryDTO(state.getCountry());
    }

    private CountryDTO setCountryDTO(Country country) {
        return new CountryDTO(country);
    }
}
