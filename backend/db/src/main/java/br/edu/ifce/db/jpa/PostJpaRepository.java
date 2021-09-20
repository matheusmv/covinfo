package br.edu.ifce.db.jpa;

import br.edu.ifce.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

}
