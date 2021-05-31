package br.edu.ifce.backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConfirmationToken {

    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime expiresAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime confirmedAt;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "fk_confirmation_token_user")
    )
    @MapsId
    private User user;
}
