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
    private String nome;
    private String initials;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<State> states = new ArrayList<>();

    public Country(Long id, String nome, String initials) {
        this.id = id;
        this.nome = nome;
        this.initials = initials;
    }
}
