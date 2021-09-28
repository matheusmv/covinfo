package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.ConfirmationToken;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ConfirmationTokenExtractor implements ResultSetExtractor<Set<ConfirmationToken>> {

    @Override
    public Set<ConfirmationToken> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        return null;
    }

    private ConfirmationToken mapConfirmationToken(ResultSet resultSet) throws SQLException {
        return new ConfirmationToken();
    }
}
