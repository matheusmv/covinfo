package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.City;
import br.edu.ifce.domain.Country;
import br.edu.ifce.domain.State;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class CityExtractor implements ResultSetExtractor<Set<City>> {

    @Override
    public Set<City> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Set<City> cities = new HashSet<>();

        while (resultSet.next()) {
            cities.add(mapCity(resultSet));
        }

        return cities;
    }

    private City mapCity(ResultSet resultSet) throws SQLException {
        return new City(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                new State(
                        resultSet.getLong("state_id"),
                        resultSet.getString("state_name"),
                        resultSet.getString("state_initials"),
                        new Country(
                                resultSet.getLong("country_id"),
                                resultSet.getString("country_name"),
                                resultSet.getString("country_initials")
                        )
                )
        );
    }
}
