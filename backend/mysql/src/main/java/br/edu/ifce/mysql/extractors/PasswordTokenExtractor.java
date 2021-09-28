package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.PasswordToken;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class PasswordTokenExtractor implements ResultSetExtractor<Set<PasswordToken>> {

    @Override
    public Set<PasswordToken> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        return null;
    }

    private PasswordToken mapPasswordToken(ResultSet resultSet) throws SQLException {
        return new PasswordToken();
    }
}
