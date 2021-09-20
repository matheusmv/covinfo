package br.edu.ifce.app.domain.useCases.utils;

public enum UserValidationResult {
    SUCCESS("Success"),
    NAME_NOT_VALID("The name parameter cannot be null or empty."),
    EMAIL_NOT_VALID("The email parameter cannot be null, empty and must have a valid format."),
    PASSWORD_NOT_VALID("The password parameter cannot be null, empty and must be longer than 8 characters.");

    private final String result;

    UserValidationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
