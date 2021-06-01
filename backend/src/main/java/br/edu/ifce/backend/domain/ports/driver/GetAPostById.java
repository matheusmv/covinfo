package br.edu.ifce.backend.domain.ports.driver;

import br.edu.ifce.backend.domain.entities.Post;

public interface GetAPostById {
    Post execute(Long id);
}
