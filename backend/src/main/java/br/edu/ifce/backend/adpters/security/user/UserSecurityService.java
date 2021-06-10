package br.edu.ifce.backend.adpters.security.user;

import br.edu.ifce.backend.domain.entities.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


public class UserSecurityService implements UserDetails {

    private final Long id;
    private final String email;
    private final String password;
    private final Boolean locked;
    private final Boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserSecurityService(Long id,
                               String email,
                               String password,
                               Boolean locked,
                               Boolean enabled,
                               Set<UserRole> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
        this.authorities = authorities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public boolean hasRole(UserRole userRole) {
        return getAuthorities().contains(new SimpleGrantedAuthority(userRole.getRole()));
    }
}
