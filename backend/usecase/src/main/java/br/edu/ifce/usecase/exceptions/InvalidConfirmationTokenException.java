package br.edu.ifce.usecase.exceptions;

public class InvalidConfirmationTokenException extends RuntimeException {
    public InvalidConfirmationTokenException(String message) {
        super(message);
    }

    public InvalidConfirmationTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
