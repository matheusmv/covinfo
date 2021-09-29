package br.edu.ifce.domain;

import br.edu.ifce.domain.enums.UserRole;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private Long id;
    private String fullName;
    private String email;
    private String password;
    private Boolean locked;
    private Boolean enabled;
    private LocalDateTime createdAt;

    private UserRole role;

    private Address address;

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

    public User(
            Long id,
            String fullName,
            String email,
            String password,
            Boolean locked,
            Boolean enabled,
            LocalDateTime createdAt,
            UserRole role,
            Address address,
            ConfirmationToken confirmationToken
    ) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.role = role;
        this.address = address;
        this.confirmationToken = confirmationToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ConfirmationToken getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(ConfirmationToken confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
