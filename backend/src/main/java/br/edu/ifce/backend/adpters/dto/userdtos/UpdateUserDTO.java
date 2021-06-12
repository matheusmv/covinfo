package br.edu.ifce.backend.adpters.dto.userdtos;

import br.edu.ifce.backend.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {

    private String fullName;
    private String email;
    private String password;

    public User toUser() {
        return new User(null, fullName, email, password);
    }
}
