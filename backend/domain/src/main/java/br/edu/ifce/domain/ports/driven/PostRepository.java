package br.edu.ifce.domain.ports.driven;

import br.edu.ifce.domain.entities.Post;

import java.util.List;

public interface PostRepository {
    void create(Post post);

    List<Post> listAll();

    Post findById(Long id);

    void update(Long id, Post post);

    void delete(Long id);
}
