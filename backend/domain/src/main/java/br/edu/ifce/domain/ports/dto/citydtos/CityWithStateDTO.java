package br.edu.ifce.domain.ports.dto.citydtos;

import br.edu.ifce.domain.entities.City;
import br.edu.ifce.domain.entities.State;
import br.edu.ifce.domain.ports.dto.statedtos.StateWithCountryDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityWithStateDTO {

    private Long id;
    private String name;
    private StateWithCountryDTO state;

    public CityWithStateDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.state = setSateWithCountyDTO(city.getState());
    }

    private StateWithCountryDTO setSateWithCountyDTO(State state) {
        return new StateWithCountryDTO(state);
    }
}
