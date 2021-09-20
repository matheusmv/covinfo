package br.edu.ifce.usecase.utils;

public enum MessageValidationResult {
    SUCCESS("Success"),
    TITLE_NOT_VALID("The title parameter cannot be null, empty."),
    CONTENT_NOT_VALID("The content parameter cannot be null or empty.");

    private final String result;

    MessageValidationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
