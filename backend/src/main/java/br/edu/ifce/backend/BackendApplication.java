package br.edu.ifce.backend;

import br.edu.ifce.backend.adpters.db.jpa.AddressJpaRepository;
import br.edu.ifce.backend.adpters.db.jpa.CountryJpaRepository;
import br.edu.ifce.backend.adpters.db.jpa.StateJpaRepository;
import br.edu.ifce.backend.domain.entities.Address;
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
	private AddressJpaRepository addressJpaRepository;

	@Autowired
	private CountryJpaRepository countryJpaRepository;

	@Autowired
	private StateJpaRepository stateJpaRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var address1 = new Address(null, "zip1", "neighborhood1", "street1");
		var country1 = new Country(null, "Brasil", "BR");
		var state1 = new State(null, "Ceará", "CE");
		var address2 = new Address(null, "zip2", "neighborhood2", "street2");
		var country2 = new Country(null, "Estados Unidos", "US");
		var state2 = new State(null, "Bahia", "BA");
		var address3 = new Address(null, "zip3", "neighborhood3", "street3");
		var country3 = new Country(null, "Espanha", "ES");
		var state3 = new State(null, "São Paulo", "SP");

		addressJpaRepository.saveAll(Arrays.asList(address1, address2, address3));
		countryJpaRepository.saveAll(Arrays.asList(country1, country2, country3));
		stateJpaRepository.saveAll(Arrays.asList(state1, state2, state3));
	}
}
