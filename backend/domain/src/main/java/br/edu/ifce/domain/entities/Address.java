package br.edu.ifce.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private Long id;
    private String zip;
    private String neighborhood;
    private String street;

    @ManyToOne
    @JoinColumn(
            name = "city_id",
            foreignKey = @ForeignKey(name = "fk_address_city"))
    private City city;

    @JsonIgnore
    @MapsId
    @OneToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "fk_address_user"))
    private User user;
}
