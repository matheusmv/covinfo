package br.edu.ifce.usecase.impl;

import br.edu.ifce.domain.Post;
import br.edu.ifce.usecase.ports.driven.PostRepository;
import br.edu.ifce.usecase.ports.driver.GetAPostById;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetAPostByIdUseCase implements GetAPostById {

    private final PostRepository postRepository;

    @Override
    public Post execute(Long id) {
        return postRepository.findById(id);
    }
}
