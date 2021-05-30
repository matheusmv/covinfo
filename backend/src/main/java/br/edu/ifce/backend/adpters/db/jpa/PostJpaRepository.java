package br.edu.ifce.backend.adpters.db.jpa;

import br.edu.ifce.backend.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long> {

}
