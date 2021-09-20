package br.edu.ifce.domain.ports.dto.userdtos;

import br.edu.ifce.domain.entities.User;
import br.edu.ifce.domain.ports.dto.addressdtos.FullAddressDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private String role;
    private FullAddressDTO address;

    public UserDTO(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.role = user.getRole().name();
        this.address = user.getAddress() == null ? null : new FullAddressDTO(user.getAddress());
    }
}
