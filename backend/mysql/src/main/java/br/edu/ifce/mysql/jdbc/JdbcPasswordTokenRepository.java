package br.edu.ifce.mysql.jdbc;

import br.edu.ifce.domain.PasswordToken;
import br.edu.ifce.mysql.Page;
import br.edu.ifce.mysql.PageRequest;
import br.edu.ifce.mysql.extractors.PasswordTokenExtractor;
import br.edu.ifce.mysql.repository.PasswordTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcPasswordTokenRepository implements PasswordTokenRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordTokenExtractor passwordTokenExtractor;

    private static final String BASE_QUERY = "SELECT " +
            "id, token, created_at, expires_at " +
            "FROM password_token";

    @Override
    public PasswordToken create(PasswordToken passwordToken) {
        var insertStatement = "INSERT INTO password_token VALUES (? ? ? ?)";

        jdbcTemplate.update(insertStatement,
                passwordToken.getId(),
                passwordToken.getToken(),
                passwordToken.getCreatedAt(),
                passwordToken.getExpiresAt()
        );

        return find(passwordToken.getId()).orElse(null);
    }

    @Override
    public PasswordToken update(PasswordToken passwordToken) {
        var updateStatement = "UPDATE password_token SET " +
                "id = ?, " +
                "token = ?, " +
                "created_at = ?, " +
                "expires_at = ? " +
                "WHERE password_token.id = ?";

        jdbcTemplate.update(updateStatement,
                passwordToken.getId(),
                passwordToken.getToken(),
                passwordToken.getCreatedAt(),
                passwordToken.getExpiresAt()
        );

        return find(passwordToken.getId()).orElse(null);
    }

    @Override
    public Optional<PasswordToken> find(Long id) {
        var selectStatement = BASE_QUERY + " WHERE password_token.id = ?";

        return Objects.requireNonNull(jdbcTemplate.query(selectStatement, passwordTokenExtractor, id)).stream().findFirst();
    }

    @Override
    // TODO: improve paging implementation
    public Page<PasswordToken> find(PageRequest page) {
        var selectStatement = BASE_QUERY + " LIMIT ?" + " OFFSET ?";
        var queryArguments = Arrays.asList(page.getSize(), page.offset()).toArray();

        var setOfPasswordTokens = jdbcTemplate.query(selectStatement, passwordTokenExtractor, queryArguments);

        return new Page<>(
                Stream.ofNullable(setOfPasswordTokens).flatMap(Collection::stream).collect(Collectors.toList()),
                page.getPage(),
                page.getSize(),
                executeCountQuery()
        );
    }

    private Integer executeCountQuery() {
        var countStatement = "SELECT count(*) AS total FROM password_token";

        return jdbcTemplate.queryForObject(countStatement, Integer.class);
    }

    @Override
    public void delete(PasswordToken passwordToken) {
        var deleteStatement = "DELETE FROM password_token WHERE password_token.id = ?";

        jdbcTemplate.update(deleteStatement, passwordToken.getId());
    }

    @Override
    public void delete(Long id) {
        var deleteStatement = "DELETE FROM password_token WHERE password_token.id = ?";

        jdbcTemplate.update(deleteStatement, id);
    }
}
