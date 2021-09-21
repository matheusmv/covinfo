package br.edu.ifce.usecase.impl;

import br.edu.ifce.domain.Post;
import br.edu.ifce.usecase.ports.driven.PostRepository;
import br.edu.ifce.usecase.ports.driver.GetAllPosts;
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
