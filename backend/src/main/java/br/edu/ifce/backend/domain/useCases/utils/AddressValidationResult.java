package br.edu.ifce.backend.domain.useCases.utils;

public enum AddressValidationResult {
    SUCCESS("Success"),
    ZIP_NOT_VALID("The zip parameter cannot be null, empty and must have a valid format."),
    NEIGHBORHOOD_NOT_VALID("The neighborhood parameter cannot be null or empty."),
    STREET_NOT_VALID("The street parameter cannot be null or empty.");

    private final String result;

    AddressValidationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
