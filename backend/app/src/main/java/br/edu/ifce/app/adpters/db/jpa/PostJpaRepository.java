package br.edu.ifce.app.adpters.db.jpa;

import br.edu.ifce.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long> {

}
