package br.edu.ifce.backend.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConfirmationToken {

    @EqualsAndHashCode.Include
    @Id
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "fk_confirmation_token_user")
    )
    @MapsId
    private User user;

    public ConfirmationToken(LocalDateTime createdAt, LocalDateTime expiresAt, User user) {
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
