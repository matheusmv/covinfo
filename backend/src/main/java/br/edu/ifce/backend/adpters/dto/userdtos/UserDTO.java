package br.edu.ifce.backend.adpters.dto.userdtos;

import br.edu.ifce.backend.adpters.dto.addressdtos.FullAddressDTO;
import br.edu.ifce.backend.domain.entities.User;
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
        this.address = new FullAddressDTO(user.getAddress());
    }
}
