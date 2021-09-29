package br.edu.ifce.mysql.jdbc;

import br.edu.ifce.domain.ConfirmationToken;
import br.edu.ifce.domain.Page;
import br.edu.ifce.domain.PageRequest;
import br.edu.ifce.mysql.extractors.ConfirmationTokenExtractor;
import br.edu.ifce.usecase.ports.driven.ConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class JdbcConfirmationTokenRepository implements ConfirmationTokenRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ConfirmationTokenExtractor confirmationTokenExtractor;

    private static final String BASE_QUERY = "SELECT " +
            "id, token, created_at, expires_at, confirmed_at " +
            "FROM confirmation_token";

    @Override
    @Transactional
    public ConfirmationToken create(ConfirmationToken confirmationToken) {
        var insertStatement = "INSERT INTO confirmation_token " +
                "(id, token, created_at, expires_at, confirmed_at) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(insertStatement,
                confirmationToken.getId(),
                confirmationToken.getToken(),
                confirmationToken.getCreatedAt(),
                confirmationToken.getExpiresAt(),
                confirmationToken.getConfirmedAt()
        );

        return find(confirmationToken.getId()).orElse(null);
    }

    @Override
    @Transactional
    public ConfirmationToken update(ConfirmationToken confirmationToken) {
        var updateStatement = "UPDATE confirmation_token SET " +
                "token = ?, " +
                "created_at = ?, " +
                "expires_at = ?, " +
                "confirmed_at = ? " +
                "WHERE confirmation_token.id = ?";

        jdbcTemplate.update(updateStatement,
                confirmationToken.getToken(),
                confirmationToken.getCreatedAt(),
                confirmationToken.getExpiresAt(),
                confirmationToken.getConfirmedAt(),
                confirmationToken.getId()
        );

        return find(confirmationToken.getId()).orElse(null);
    }

    @Override
    public Optional<ConfirmationToken> find(Long id) {
        var selectStatement = BASE_QUERY + " WHERE confirmation_token.id = ?";

        return Objects.requireNonNull(jdbcTemplate.query(selectStatement, confirmationTokenExtractor, id)).stream().findFirst();
    }

    @Override
    public Optional<ConfirmationToken> find(String token) {
        var selectStatement = BASE_QUERY + " WHERE confirmation_token.token = ?";

        return Objects.requireNonNull(jdbcTemplate.query(selectStatement, confirmationTokenExtractor, token)).stream().findFirst();
    }

    @Override
    // TODO: improve paging implementation
    public Page<ConfirmationToken> find(PageRequest page) {
        var selectStatement = BASE_QUERY + " LIMIT ?" + " OFFSET ?";
        var queryArguments = Arrays.asList(page.getSize(), page.offset()).toArray();

        var setOfConfirmationTokens = jdbcTemplate.query(selectStatement, confirmationTokenExtractor, queryArguments);

        return new Page<>(
                Stream.ofNullable(setOfConfirmationTokens).flatMap(Collection::stream).collect(Collectors.toList()),
                page.getPage(),
                page.getSize(),
                executeCountQuery()
        );
    }

    private Integer executeCountQuery() {
        var countStatement = "SELECT count(*) AS total FROM confirmation_token";

        return jdbcTemplate.queryForObject(countStatement, Integer.class);
    }

    @Override
    @Transactional
    public void delete(ConfirmationToken confirmationToken) {
        var deleteStatement = "DELETE FROM confirmation_token WHERE confirmation_token.id = ?";

        jdbcTemplate.update(deleteStatement, confirmationToken.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var deleteStatement = "DELETE FROM confirmation_token WHERE confirmation_token.id = ?";

        jdbcTemplate.update(deleteStatement, id);
    }
}
