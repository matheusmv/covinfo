package br.edu.ifce.usecase.ports.requests;

import br.edu.ifce.domain.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewMessageDTO {

    private String title;
    private String content;

    public NewMessageDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Message toMessage() {
        return new Message(null, title, content, null, null);
    }
}
