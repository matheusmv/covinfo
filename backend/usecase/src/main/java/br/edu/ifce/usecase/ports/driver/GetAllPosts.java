package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.domain.Post;

import java.util.List;

public interface GetAllPosts {
    List<Post> execute();
}
