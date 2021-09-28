package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class UserExtractor implements ResultSetExtractor<Set<User>> {

    @Override
    public Set<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        return null;
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        return new User();
    }
}
