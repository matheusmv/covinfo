package br.edu.ifce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "city")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        City city = (City) o;
        return Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
