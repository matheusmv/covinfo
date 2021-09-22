package br.edu.ifce.domain;

import br.edu.ifce.domain.enums.UserRole;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private Boolean locked;
    private Boolean enabled;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ConfirmationToken confirmationToken;

    public User() {
        this.locked = true;
        this.enabled = false;
        this.role = UserRole.USER;
    }

    public User(Long id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.locked = true;
        this.enabled = false;
        this.role = UserRole.USER;
    }
}
