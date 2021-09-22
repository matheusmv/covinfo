package br.edu.ifce.usecase.ports.requests;

import br.edu.ifce.domain.User;
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
