package br.edu.ifce.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "state_id",
            foreignKey = @ForeignKey(name = "fk_city_state"))
    private State state;
}
