package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.Address;
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
public class AddressExtractor implements ResultSetExtractor<Set<Address>> {

    @Override
    public Set<Address> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Set<Address> addresses = new HashSet<>();

        while (resultSet.next()) {
            addresses.add(mapAddress(resultSet));
        }

        return addresses;
    }

    // TODO: remove the user attribute from the Address entity
    private Address mapAddress(ResultSet resultSet) throws SQLException {
        return new Address(
                resultSet.getLong("id"),
                resultSet.getString("zip"),
                resultSet.getString("neighborhood"),
                resultSet.getString("street"),
                new City(
                        resultSet.getLong("city_id"),
                        resultSet.getString("city_name"),
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
                ),
                null
        );
    }
}
