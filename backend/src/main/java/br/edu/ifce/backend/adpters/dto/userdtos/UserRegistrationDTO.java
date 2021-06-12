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
    private String neighborhood;
    private String street;
    private String zip;

    public User toUserWithAddress() {
        var user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(createAddress(zip, neighborhood, street, user));

        return user;
    }

    private Address createAddress(String zip, String neighborhood, String street, User user) {
        return new Address(null, zip, neighborhood, street, null, user);
    }
}
