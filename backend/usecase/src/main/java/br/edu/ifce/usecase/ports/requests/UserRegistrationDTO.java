package br.edu.ifce.usecase.ports.requests;

import br.edu.ifce.domain.Address;
import br.edu.ifce.domain.User;
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
