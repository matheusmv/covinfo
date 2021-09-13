package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Post;
import br.edu.ifce.backend.domain.ports.driven.PostRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAPostById;
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
