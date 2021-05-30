package br.edu.ifce.backend;

import br.edu.ifce.backend.adpters.db.jpa.StateJpaRepository;
import br.edu.ifce.backend.domain.entities.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	private StateJpaRepository stateJpaRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var state1 = new State(null, "Ceará", "CE");
		var state2 = new State(null, "Bahia", "BA");
		var state3 = new State(null, "São Paulo", "SP");

		stateJpaRepository.saveAll(Arrays.asList(state1, state2, state3));
	}
}
