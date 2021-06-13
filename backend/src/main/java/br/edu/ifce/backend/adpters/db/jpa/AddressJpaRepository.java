package br.edu.ifce.backend.adpters.db.jpa;

import br.edu.ifce.backend.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AddressJpaRepository extends JpaRepository<Address, Long> {

    @Transactional(readOnly = true)
    Address findByUserId(Long id);
}
