package br.edu.ifce.backend.domain.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zip;
    private String neighborhood;
    private String street;

    @ManyToOne
    @JoinColumn(
            name = "city_id",
            foreignKey = @ForeignKey(name = "fk_address_city")
    )
    private City city;
}
