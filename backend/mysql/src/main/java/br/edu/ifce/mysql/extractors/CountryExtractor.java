package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.Country;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class CountryExtractor implements ResultSetExtractor<Set<Country>> {

    @Override
    public Set<Country> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        return null;
    }

    private Country mapCountry(ResultSet resultSet) throws SQLException {
        return new Country();
    }
}
