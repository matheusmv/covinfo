package br.edu.ifce.mysql.extractors;

import br.edu.ifce.domain.State;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class StateExtractor implements ResultSetExtractor<Set<State>> {

    @Override
    public Set<State> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        return null;
    }

    private State mapState(ResultSet resultSet) throws SQLException {
        return new State();
    }
}
