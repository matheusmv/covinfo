package br.edu.ifce.domain.exceptions;

public class InvalidConfirmationTokenException extends RuntimeException {
    public InvalidConfirmationTokenException(String message) {
        super(message);
    }

    public InvalidConfirmationTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
