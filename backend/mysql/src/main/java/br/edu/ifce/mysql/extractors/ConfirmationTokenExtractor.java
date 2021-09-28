package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.ConfirmationToken;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class ConfirmationTokenExtractor implements ResultSetExtractor<Set<ConfirmationToken>> {

    @Override
    public Set<ConfirmationToken> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Set<ConfirmationToken> confirmationTokens = new HashSet<>();

        while (resultSet.next()) {
            confirmationTokens.add(mapConfirmationToken(resultSet));
        }

        return confirmationTokens;
    }

    // TODO: remove the user attribute from the ConfirmationToken entity
    private ConfirmationToken mapConfirmationToken(ResultSet resultSet) throws SQLException {
        return new ConfirmationToken(
                resultSet.getLong("id"),
                resultSet.getString("token"),
                resultSet.getTimestamp("created_at").toLocalDateTime(),
                resultSet.getTimestamp("expires_at").toLocalDateTime(),
                resultSet.getTimestamp("confirmed_at").toLocalDateTime(),
                null
        );
    }
}
