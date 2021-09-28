package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.User;
import br.edu.ifce.domain.enums.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserExtractor implements ResultSetExtractor<Set<User>> {

    @Override
    public Set<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Set<User> users = new HashSet<>();

        while (resultSet.next()) {
            users.add(mapUser(resultSet));
        }

        return users;
    }

    // TODO: analyze the need to fetch the user with address and confirmation token
    private User mapUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("full_name"),
                resultSet.getString("email"),
                "",
                resultSet.getBoolean("locked"),
                resultSet.getBoolean("enabled"),
                resultSet.getTimestamp("created_at").toLocalDateTime(),
                UserRole.valueOf(resultSet.getString("role")),
                null,
                null
        );
    }
}
