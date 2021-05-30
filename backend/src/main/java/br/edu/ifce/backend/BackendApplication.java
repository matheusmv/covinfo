package br.edu.ifce.backend;

import br.edu.ifce.backend.adpters.db.jpa.CountryJpaRepository;
import br.edu.ifce.backend.adpters.db.jpa.StateJpaRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var country1 = new Country(null, "Brasil", "BR");
		var state1 = new State(null, "Ceará", "CE");
		var country2 = new Country(null, "Estados Unidos", "US");
		var state2 = new State(null, "Bahia", "BA");
		var country3 = new Country(null, "Espanha", "ES");
		var state3 = new State(null, "São Paulo", "SP");

		countryJpaRepository.saveAll(Arrays.asList(country1, country2, country3));
		stateJpaRepository.saveAll(Arrays.asList(state1, state2, state3));
	}
}
