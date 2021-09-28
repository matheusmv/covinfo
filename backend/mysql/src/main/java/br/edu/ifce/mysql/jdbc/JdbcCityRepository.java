package br.edu.ifce.mysql.jdbc;

import br.edu.ifce.domain.City;
import br.edu.ifce.mysql.extractors.CityExtractor;
import br.edu.ifce.mysql.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcCityRepository implements CityRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CityExtractor cityExtractor;

    private static final String BASE_QUERY = "SELECT " +
            "city.id, city.name, " +
            "state.id AS state_id, state.name AS state_name, state.initials AS state_initials, " +
            "country.id AS country_id, country.name AS country_name, country.initials AS country_initials " +
            "FROM city " +
            "INNER JOIN state ON state.id = city.state_id " +
            "INNER JOIN country ON country.id = state.country_id";

    @Override
    public Optional<City> findByNameAndStateInitials(String name, String stateInitials) {
        var selectStatement = BASE_QUERY + " WHERE city.name = ? AND state.initials = ?";
        var queryArguments = Arrays.asList(name, stateInitials).toArray();

        return Objects.requireNonNull(jdbcTemplate.query(selectStatement, cityExtractor, name, queryArguments))
                .stream()
                .findFirst();
    }
}
