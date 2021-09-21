package br.edu.ifce.usecase.ports.dto.confirmationtokendtos;

import br.edu.ifce.domain.ConfirmationToken;
import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.ports.dto.userdtos.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConfirmationTokenWithUserDTO {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    private UserDTO user;

    public ConfirmationTokenWithUserDTO(ConfirmationToken confirmationToken) {
        this.id = confirmationToken.getId();
        this.createdAt = confirmationToken.getCreatedAt();
        this.expiresAt = confirmationToken.getExpiresAt();
        this.confirmedAt = confirmationToken.getConfirmedAt();
        this.user = setUserDTO(confirmationToken.getUser());
    }

    private UserDTO setUserDTO(User user) {
        return new UserDTO(user);
    }
}
