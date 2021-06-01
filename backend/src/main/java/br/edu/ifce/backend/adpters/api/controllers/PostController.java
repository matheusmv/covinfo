package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.postdtos.PostDTO;
import br.edu.ifce.backend.adpters.dto.postdtos.PostWithContentDTO;
import br.edu.ifce.backend.domain.ports.driver.GetAPostById;
import br.edu.ifce.backend.domain.ports.driver.GetAllPosts;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

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
