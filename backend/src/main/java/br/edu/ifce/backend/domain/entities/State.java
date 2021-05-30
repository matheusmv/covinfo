package br.edu.ifce.backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class State {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String initials;

    @ManyToOne
    @JoinColumn(
            name = "country_id",
            foreignKey = @ForeignKey(name = "fk_state_country")
    )
    private Country country;

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private List<City> cities = new ArrayList<>();

    public State(Long id, String name, String initials) {
        this.id = id;
        this.name = name;
        this.initials = initials;
    }
}
