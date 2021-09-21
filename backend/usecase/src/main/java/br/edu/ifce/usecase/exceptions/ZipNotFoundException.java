package br.edu.ifce.usecase.exceptions;

public class ZipNotFoundException extends RuntimeException {
    public ZipNotFoundException(String message) {
        super(message);
    }

    public ZipNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
