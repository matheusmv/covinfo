package br.edu.ifce.domain.enums;

public enum UserPermissions {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    MESSAGE_READ("message:read"),
    MESSAGE_WRITE("message:write"),
    POST_READ("post:read"),
    POST_WRITE("post:write");

    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
