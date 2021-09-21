package br.edu.ifce.domain;

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
@Table(name = "state")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class State {

    @Id
    private Long id;
    private String name;
    private String initials;

    @ManyToOne
    @JoinColumn(
            name = "country_id",
            foreignKey = @ForeignKey(name = "fk_state_country"))
    private Country country;
}
