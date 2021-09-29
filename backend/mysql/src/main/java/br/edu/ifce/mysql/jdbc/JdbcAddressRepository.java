package br.edu.ifce.mysql.jdbc;

import br.edu.ifce.domain.Address;
import br.edu.ifce.domain.Page;
import br.edu.ifce.domain.PageRequest;
import br.edu.ifce.mysql.extractors.AddressExtractor;
import br.edu.ifce.usecase.ports.driven.AddressRepository;
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
public class JdbcAddressRepository implements AddressRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AddressExtractor addressExtractor;

    private static final String BASE_QUERY = "SELECT " +
            "address.id, address.zip, address.neighborhood, address.street, " +
            "city.id AS city_id, city.name AS city_name, " +
            "state.id AS state_id, state.name AS state_name, state.initials AS state_initials, " +
            "country.id AS country_id, country.name AS country_name, country.initials AS country_initials " +
            "FROM address " +
            "INNER JOIN city ON city.id = address.city_id " +
            "INNER JOIN state ON state.id = city.state_id " +
            "INNER JOIN country ON country.id = state.country_id";

    @Override
    @Transactional
    public Address create(Address address) {
        var insertStatement = "INSERT INTO address " +
                "(id, city_id, zip, neighborhood, street) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(insertStatement,
                address.getId(),
                address.getCity().getId(),
                address.getZip(),
                address.getNeighborhood(),
                address.getStreet()
        );

        return find(address.getId()).orElse(null);
    }

    @Override
    @Transactional
    public Address update(Address address) {
        var updateStatement = "UPDATE address " +
                "SET city_id = ?, " +
                "zip = ?, " +
                "neighborhood = ?, " +
                "street = ? " +
                "WHERE address.id = ?";

        jdbcTemplate.update(updateStatement,
                address.getCity().getId(),
                address.getZip(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getId()
        );

        return find(address.getId()).orElse(null);
    }

    @Override
    public Optional<Address> find(Long id) {
        var selectStatement = BASE_QUERY + " WHERE address.id = ?";

        return Objects.requireNonNull(jdbcTemplate.query(selectStatement, addressExtractor, id)).stream().findFirst();
    }

    @Override
    // TODO: improve paging implementation
    public Page<Address> find(PageRequest page) {
        var selectStatement = BASE_QUERY + " LIMIT ?" + " OFFSET ?";
        var queryArguments = Arrays.asList(page.getSize(), page.offset()).toArray();

        var setOfAddresses = jdbcTemplate.query(selectStatement, addressExtractor, queryArguments);

        return new Page<>(
                Stream.ofNullable(setOfAddresses).flatMap(Collection::stream).collect(Collectors.toList()),
                page.getPage(),
                page.getSize(),
                executeCountQuery()
        );
    }

    private Integer executeCountQuery() {
        var countStatement = "SELECT count(*) AS total FROM address";

        return jdbcTemplate.queryForObject(countStatement, Integer.class);
    }

    @Override
    @Transactional
    public void delete(Address address) {
        var deleteStatement = "DELETE FROM address WHERE address.id = ?";

        jdbcTemplate.update(deleteStatement, address.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var deleteStatement = "DELETE FROM address WHERE address.id = ?";

        jdbcTemplate.update(deleteStatement, id);
    }
}
