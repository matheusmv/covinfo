package br.edu.ifce.usecase.ports.dto.confirmationtokendtos;

import br.edu.ifce.domain.ConfirmationToken;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConfirmationTokenDTO {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    private Long userId;

    public ConfirmationTokenDTO(ConfirmationToken confirmationToken) {
        this.id = confirmationToken.getId();
        this.createdAt = confirmationToken.getCreatedAt();
        this.expiresAt = confirmationToken.getExpiresAt();
        this.confirmedAt = confirmationToken.getConfirmedAt();
        this.userId = confirmationToken.getUser().getId();
    }
}
