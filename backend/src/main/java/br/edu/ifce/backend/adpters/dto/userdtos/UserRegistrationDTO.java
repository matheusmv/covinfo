package br.edu.ifce.backend.adpters.dto.userdtos;

import br.edu.ifce.backend.domain.entities.Address;
import br.edu.ifce.backend.domain.entities.User;
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
    private Address address;

    public User toUserWithAddress() {
        var user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);

        address.setUser(user);
        user.setAddress(address);

        return user;
    }
}
