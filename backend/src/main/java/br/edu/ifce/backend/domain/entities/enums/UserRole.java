package br.edu.ifce.backend.domain.entities.enums;

public enum UserRole {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER"),
    CONTENT_MANAGER(3, "ROLE_CONTENT_MANAGER");

    private final int code;
    private final String role;

    UserRole(int code, String role) {
        this.code = code;
        this.role = role;
    }

    public int getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }

    public static UserRole toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (UserRole role : UserRole.values()) {
            if (code.equals(role.getCode())) {
                return role;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
