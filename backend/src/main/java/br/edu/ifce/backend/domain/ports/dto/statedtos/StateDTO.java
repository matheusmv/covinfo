package br.edu.ifce.backend.domain.ports.dto.statedtos;

import br.edu.ifce.backend.domain.entities.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateDTO {
    private Long id;
    private String name;
    private String initials;
    private Long countryId;

    public StateDTO(State state) {
        this.id = state.getId();
        this.name = state.getName();
        this.initials = state.getInitials();
        this.countryId = state.getCountry().getId();
    }
}
