package br.edu.ifce.usecase.ports.driver;

import br.edu.ifce.domain.Post;

public interface GetAPostById {
    Post execute(Long id);
}
