package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.Post;

import java.util.List;

public interface GetAllPosts {
    List<Post> execute();
}
