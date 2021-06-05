package br.edu.ifce.backend.adpters.utils;

import br.edu.ifce.backend.adpters.db.jpa.*;
import br.edu.ifce.backend.domain.entities.*;
import br.edu.ifce.backend.domain.entities.enums.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Service
@AllArgsConstructor
public class TestService {

    private final CountryJpaRepository countryJpaRepository;
    private final StateJpaRepository stateJpaRepository;
    private final CityJpaRepository cityJpaRepository;
    private final AddressJpaRepository addressJpaRepository;
    private final ConfirmationTokenJpaRepository confirmationTokenJpaRepository;
    private final MessageJpaRepository messageJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public void InstantiateTestDataBase() {
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

        var city1 = new City(null, "Aracati", state1);
        var city2 = new City(null, "Fortaleza", state1);
        var city3 = new City(null, "Lapão", state2);

        countryJpaRepository.saveAll(Arrays.asList(country1, country2, country3));
        stateJpaRepository.saveAll(Arrays.asList(state1, state2, state3));
        cityJpaRepository.saveAll(Arrays.asList(city1, city2, city3));

        var user1 = new User(null, "José Almeida", "jose@email.com", "password");
        var address1 = new Address(null, "62800000", "centro", "Rua 1", city1, user1);
        var token1 = new ConfirmationToken(LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user1);

        var user2 = new User(null, "Maria Alves", "maria@email.com", "password");
        var address2 = new Address(null, "62800000", "aeroporto", "Rua 2", city1, user2);
        var token2 = new ConfirmationToken(LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user2);

        var user3 = new User(null, "Pedro Paulo", "pedro@email.com", "password");
        var address3 = new Address(null, "62800000", "aeroporto", "Rua 3", city1, user3);
        var token3 = new ConfirmationToken(LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user3);

        user1.setAddress(address1);
        user2.setAddress(address2);
        user3.setAddress(address3);

        user1.setRole(UserRole.USER);
        user2.setRole(UserRole.CONTENT_MANAGER);
        user3.setRole(UserRole.ADMIN);

        user1.setConfirmationToken(token1);
        user2.setConfirmationToken(token2);
        user3.setConfirmationToken(token3);

        user1.setCreatedAt(LocalDateTime.now());
        user2.setCreatedAt(LocalDateTime.now());
        user3.setCreatedAt(LocalDateTime.now());

        userJpaRepository.saveAll(Arrays.asList(user1, user2, user3));
        addressJpaRepository.saveAll(Arrays.asList(address1, address2, address3));
        confirmationTokenJpaRepository.saveAll(Arrays.asList(token1, token2, token3));

        var post1 = new Post(null, "Informações 1", "Descrição do post 1", "Conteúdo do post 1", LocalDateTime.now(), user2);
        var post2 = new Post(null, "Informações 2", "Descrição do post 2", "Conteúdo do post 2", LocalDateTime.now(), user2);
        var post3 = new Post(null, "Informações 3", "Descrição do post 3", "Conteúdo do post 3", LocalDateTime.now(), user2);

        user2.setPosts(Arrays.asList(post1, post2, post3));

        var message1 = new Message(null, "Message 1", "conteúdo da mensagem 1", LocalDateTime.now(), user1);

        user1.setMessages(Collections.singletonList(message1));

        postJpaRepository.saveAll(Arrays.asList(post1, post2, post3));
        messageJpaRepository.save(message1);
    }
}
