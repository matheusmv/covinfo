package br.edu.ifce.backend.domain.ports.dto.postdtos;

import br.edu.ifce.backend.domain.entities.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostWithContentDTO {

    private Long id;
    private String title;
    private String description;
    private String content;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;

    public PostWithContentDTO (Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
    }
}
