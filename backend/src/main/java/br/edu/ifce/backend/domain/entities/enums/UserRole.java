package br.edu.ifce.backend.domain.entities.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static br.edu.ifce.backend.domain.entities.enums.UserPermissions.*;

public enum UserRole {
    ADMIN(1, "ROLE_ADMIN", Sets.newHashSet(USER_READ, USER_WRITE, MESSAGE_READ, MESSAGE_WRITE, POST_READ, POST_WRITE)),
    USER(2, "ROLE_USER", Sets.newHashSet(USER_READ, USER_WRITE, MESSAGE_WRITE, POST_READ)),
    CONTENT_MANAGER(3, "ROLE_CONTENT_MANAGER", Sets.newHashSet(USER_READ, MESSAGE_READ, MESSAGE_WRITE, POST_READ, POST_WRITE));

    private final int code;
    private final String role;
    private final Set<UserPermissions> permissions;

    UserRole(int code, String role, Set<UserPermissions> permissions) {
        this.code = code;
        this.role = role;
        this.permissions = permissions;
    }

    public int getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }

    public static UserRole toEnum(Integer code) {
        return Arrays.stream(UserRole.values())
                .filter(userRole -> code.equals(userRole.getCode()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));
    }

    public static Set<SimpleGrantedAuthority> getGrantedAuthorities(Integer code) {
        UserRole role = toEnum(code);

        Set<SimpleGrantedAuthority> permissions = role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority(role.getRole()));

        return permissions;
    }
}
