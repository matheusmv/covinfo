package br.edu.ifce.backend.adpters.dto.messagedtos;

import br.edu.ifce.backend.domain.entities.Message;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDTO {

    private Long id;
    private String title;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;
    private Long userId;

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.title = message.getTitle();
        this.createdAt = message.getCreatedAt();
        this.userId = message.getUser().getId();
    }
}
