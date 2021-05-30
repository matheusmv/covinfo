package br.edu.ifce.backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class City {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "state_id",
            foreignKey = @ForeignKey(name = "fk_city_state")
    )
    private State state;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<Address> addresses = new ArrayList<>();

    public City(Long id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
