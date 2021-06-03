package br.edu.ifce.backend.domain.exceptions;

public class InvalidZipException extends RuntimeException {
    public InvalidZipException(String message) {
        super(message);
    }

    public InvalidZipException(String message, Throwable cause) {
        super(message, cause);
    }
}
