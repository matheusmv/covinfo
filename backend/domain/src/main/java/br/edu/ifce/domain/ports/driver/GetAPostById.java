package br.edu.ifce.domain.ports.driver;

import br.edu.ifce.domain.entities.Post;

public interface GetAPostById {
    Post execute(Long id);
}
