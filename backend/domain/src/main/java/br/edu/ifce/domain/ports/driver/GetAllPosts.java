package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.entities.Post;

import java.util.List;

public interface GetAllPosts {
    List<Post> execute();
}
