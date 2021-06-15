package br.edu.ifce.backend.adpters.dto.userdtos;

import br.edu.ifce.backend.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SimpleUserDTO {

    private Long id;
    private String fullName;
    private String role;
    private LocalDateTime createdAt;

    public SimpleUserDTO(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.role = user.getRole().name();
        this.createdAt = user.getCreatedAt();
    }
}