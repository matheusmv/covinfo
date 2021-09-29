package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.PasswordToken;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class PasswordTokenExtractor implements ResultSetExtractor<Set<PasswordToken>> {

    @Override
    public Set<PasswordToken> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Set<PasswordToken> passwordTokens = new HashSet<>();

        while (resultSet.next()) {
            passwordTokens.add(mapPasswordToken(resultSet));
        }

        return passwordTokens;
    }

    // TODO: remove the user attribute from the PasswordToken entity
    private PasswordToken mapPasswordToken(ResultSet resultSet) throws SQLException {
        return new PasswordToken(
                resultSet.getLong("id"),
                resultSet.getString("token"),
                resultSet.getObject("created_at", LocalDateTime.class),
                resultSet.getObject("expires_at", LocalDateTime.class),
                null
        );
    }
}
