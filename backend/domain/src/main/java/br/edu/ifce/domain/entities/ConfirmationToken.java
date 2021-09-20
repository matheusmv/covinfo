package br.edu.ifce.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "confirmation_token")
@Data
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @MapsId
    @OneToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "fk_confirmation_token_user"))
    private User user;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public boolean hasExpired() {
        return expiresAt.isBefore(LocalDateTime.now());
    }
}
