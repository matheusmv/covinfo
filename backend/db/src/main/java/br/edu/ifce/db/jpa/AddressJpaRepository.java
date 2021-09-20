package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AddressJpaRepository extends JpaRepository<Address, Long> {

    @Transactional(readOnly = true)
    Optional<Address> findByUserId(Long id);
}
