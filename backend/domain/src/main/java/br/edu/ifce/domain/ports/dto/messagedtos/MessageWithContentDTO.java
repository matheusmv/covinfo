package br.edu.ifce.domain.ports.dto.messagedtos;

import br.edu.ifce.domain.entities.Message;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageWithContentDTO {

    private Long id;
    private String title;
    private String author;
    private String content;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;
    private Long userId;

    public MessageWithContentDTO(Message message) {
        this.id = message.getId();
        this.title = message.getTitle();
        this.author = message.getUser().getFullName();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.userId = message.getUser().getId();
    }
}
