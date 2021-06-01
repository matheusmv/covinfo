package br.edu.ifce.backend.adpters.dto.citydtos;

import br.edu.ifce.backend.domain.entities.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDTO {

    private Long id;
    private String name;
    private Long stateId;

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.stateId = city.getState().getId();
    }
}
