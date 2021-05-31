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
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Country {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String initials;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<State> states = new ArrayList<>();

    public Country(Long id, String name, String initials) {
        this.id = id;
        this.name = name;
        this.initials = initials;
    }
}
