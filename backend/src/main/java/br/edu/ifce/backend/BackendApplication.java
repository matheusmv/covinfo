package br.edu.ifce.backend;

import br.edu.ifce.backend.adpters.db.jpa.*;
import br.edu.ifce.backend.domain.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private CountryJpaRepository countryJpaRepository;
    @Autowired
    private StateJpaRepository stateJpaRepository;
    @Autowired
    private CityJpaRepository cityJpaRepository;
    @Autowired
    private AddressJpaRepository addressJpaRepository;
    @Autowired
    private ConfirmationTokenJpaRepository confirmationTokenJpaRepository;
    @Autowired
    private PostJpaRepository postJpaRepository;

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

        var address1 = new Address(null, "62800000", "centro", "Rua 1", city1);
        var address2 = new Address(null, "62800000", "aeroporto", "Rua 2", city1);
        var address3 = new Address(null, "62800000", "aeroporto", "Rua 3", city1);

        city1.getAddresses().addAll(Arrays.asList(address1, address2, address3));

        countryJpaRepository.saveAll(Arrays.asList(country1, country2, country3));
        stateJpaRepository.saveAll(Arrays.asList(state1, state2, state3));
        cityJpaRepository.saveAll(Arrays.asList(city1, city2, city3));
        addressJpaRepository.saveAll(Arrays.asList(address1, address2, address3));

        var token1 = new ConfirmationToken(null, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), null);

        confirmationTokenJpaRepository.save(token1);

        var post1 = new Post(null, "Informações 1", "Descrição do post 1", "Conteúdo do post 1", LocalDateTime.now());
        var post2 = new Post(null, "Informações 2", "Descrição do post 2", "Conteúdo do post 2", LocalDateTime.now());
        var post3 = new Post(null, "Informações 3", "Descrição do post 3", "Conteúdo do post 3", LocalDateTime.now());

        postJpaRepository.saveAll(Arrays.asList(post1, post2, post3));
    }
}
