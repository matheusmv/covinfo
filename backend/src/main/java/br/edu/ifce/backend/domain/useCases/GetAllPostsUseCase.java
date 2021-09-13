package br.edu.ifce.backend.domain.useCases;

import br.edu.ifce.backend.domain.entities.Post;
import br.edu.ifce.backend.domain.ports.driven.PostRepository;
import br.edu.ifce.backend.domain.ports.driver.GetAllPosts;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetAllPostsUseCase implements GetAllPosts {

    private final PostRepository postRepository;

    @Override
    public List<Post> execute() {
        return postRepository.listAll();
    }
}
