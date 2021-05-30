package br.edu.ifce.backend;

import br.edu.ifce.backend.adpters.db.jpa.CityJpaRepository;
import br.edu.ifce.backend.adpters.db.jpa.CountryJpaRepository;
import br.edu.ifce.backend.adpters.db.jpa.StateJpaRepository;
import br.edu.ifce.backend.domain.entities.City;
import br.edu.ifce.backend.domain.entities.Country;
import br.edu.ifce.backend.domain.entities.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private CountryJpaRepository countryJpaRepository;
    @Autowired
    private StateJpaRepository stateJpaRepository;
    @Autowired
    private CityJpaRepository cityJpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        var country1 = new Country(null, "Brasil", "BR");
        var country2 = new Country(null, "Estados Unidos", "US");
        var country3 = new Country(null, "Espanha", "ES");

        var state1 = new State(null, "Ceará", "CE");
        var state2 = new State(null, "Bahia", "BA");
        var state3 = new State(null, "São Paulo", "SP");

        state1.setCountry(country1);
        state2.setCountry(country1);
        state3.setCountry(country1);

        country1.getStates().addAll(Arrays.asList(state1, state2, state3));

        var city1 = new City(null, "Arcati", state1);
        var city2 = new City(null, "Fortaleza", state1);
        var city3 = new City(null, "Lapão", state2);

        countryJpaRepository.saveAll(Arrays.asList(country1, country2, country3));
        stateJpaRepository.saveAll(Arrays.asList(state1, state2, state3));
        cityJpaRepository.saveAll(Arrays.asList(city1, city2, city3));
    }
}
