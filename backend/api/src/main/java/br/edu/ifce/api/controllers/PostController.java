package br.edu.ifce.api.controllers;

import br.edu.ifce.api.docs.PostControllerDocs;
import br.edu.ifce.usecase.ports.driver.GetAPostById;
import br.edu.ifce.usecase.ports.driver.GetAllPosts;
import br.edu.ifce.usecase.ports.responses.PostDTO;
import br.edu.ifce.usecase.ports.responses.PostWithContentDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PostController implements PostControllerDocs {

    private final GetAllPosts getAllPosts;
    private final GetAPostById getAPostById;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        var listOfPosts = getAllPosts.execute()
                .stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listOfPosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostWithContentDTO> getAPostById(@PathVariable Long id) {
        var post = getAPostById.execute(id);

        return ResponseEntity.ok().body(new PostWithContentDTO(post));
    }
}
