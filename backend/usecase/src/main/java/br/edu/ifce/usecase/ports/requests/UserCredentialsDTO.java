package br.edu.ifce.usecase.ports.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCredentialsDTO {
    private String email;
    private String password;
}
