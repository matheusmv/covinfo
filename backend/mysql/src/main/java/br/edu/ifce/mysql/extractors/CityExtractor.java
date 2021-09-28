package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.City;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class CityExtractor implements ResultSetExtractor<Set<City>> {

    @Override
    public Set<City> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        return null;
    }

    private City mapCity(ResultSet resultSet) throws SQLException {
        return new City();
    }
}
