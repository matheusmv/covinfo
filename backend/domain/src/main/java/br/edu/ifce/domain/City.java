package br.edu.ifce.domain;

import java.util.Objects;

public class City {

    private Long id;
    private String name;
    private State state;

    public City() {

    }

    public City(Long id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id.equals(city.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
