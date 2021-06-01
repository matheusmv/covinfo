package br.edu.ifce.backend.adpters.dto.userdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegistrationDTO {
    private String fullName;
    private String email;
    private String password;

    private String zip;
    private String neighborhood;
    private String street;

    private Long stateId;
}
