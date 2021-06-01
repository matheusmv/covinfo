package br.edu.ifce.backend.domain.ports.driven;

import br.edu.ifce.backend.domain.entities.Post;

import java.util.List;

public interface PostRepository {
    void create(Post post);

    List<Post> listAll();

    Post findById(Long id);

    void update(Long id, Post post);

    void delete(Long id);
}
