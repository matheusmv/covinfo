package br.edu.ifce.mysql.jdbc;

import br.edu.ifce.domain.Page;
import br.edu.ifce.domain.PageRequest;
import br.edu.ifce.domain.User;
import br.edu.ifce.mysql.extractors.UserExtractor;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserExtractor userExtractor;

    private static final String BASE_QUERY = "SELECT " +
            "id, full_name, email, password, locked, enabled, created_at, role " +
            "FROM user";

    @Override
    @Transactional
    public User create(User user) {
        var insertStatement = "INSERT INTO user " +
                "(full_name, email, password, locked, enabled, created_at, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            var preparedStatement = connection.prepareStatement(insertStatement, new String[]{"id"});

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBoolean(4, user.getLocked());
            preparedStatement.setBoolean(5, user.getEnabled());
            preparedStatement.setObject(6, user.getCreatedAt());
            preparedStatement.setString(7, user.getRole().toString());

            return preparedStatement;
        }, keyHolder);

        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return find(user.getId()).orElse(null);
    }

    @Override
    @Transactional
    public User update(User user) {
        var updateStatement = "UPDATE user SET " +
                "full_name = ?, " +
                "email = ?, " +
                "password = ?, " +
                "locked = ?, " +
                "enabled = ?, " +
                "created_at = ?, " +
                "role = ? " +
                "WHERE user.id = ?";

        jdbcTemplate.update(updateStatement,
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getLocked(),
                user.getEnabled(),
                user.getCreatedAt(),
                user.getRole().toString(),
                user.getId()
        );

        return find(user.getId()).orElse(null);
    }

    @Override
    public Optional<User> find(Long id) {
        var selectStatement = BASE_QUERY + " WHERE user.id = ?";

        return Objects.requireNonNull(jdbcTemplate.query(selectStatement, userExtractor, id)).stream().findFirst();
    }

    @Override
    public Optional<User> find(String email) {
        var selectStatement = BASE_QUERY + " WHERE user.email = ?";

        return Objects.requireNonNull(jdbcTemplate.query(selectStatement, userExtractor, email)).stream().findFirst();
    }

    @Override
    public Page<User> find(PageRequest page) {
        var selectStatement = BASE_QUERY + " LIMIT ?" + " OFFSET ?";
        var queryArguments = Arrays.asList(page.getSize(), page.offset()).toArray();

        var setOfPasswordTokens = jdbcTemplate.query(selectStatement, userExtractor, queryArguments);

        return new Page<>(
                Stream.ofNullable(setOfPasswordTokens).flatMap(Collection::stream).collect(Collectors.toList()),
                page.getPage(),
                page.getSize(),
                executeCountQuery()
        );
    }

    private Integer executeCountQuery() {
        var countStatement = "SELECT count(*) AS total FROM user";

        return jdbcTemplate.queryForObject(countStatement, Integer.class);
    }

    @Override
    @Transactional
    public void delete(User user) {
        var deleteStatement = "DELETE FROM user WHERE user.id = ?";

        jdbcTemplate.update(deleteStatement, user.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var deleteStatement = "DELETE FROM user WHERE user.id = ?";

        jdbcTemplate.update(deleteStatement, id);
    }

    @Override
    public boolean emailIsAlreadyInUse(String email) {
        return find(email).isPresent();
    }
}
