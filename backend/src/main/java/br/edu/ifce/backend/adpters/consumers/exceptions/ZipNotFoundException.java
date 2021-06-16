package br.edu.ifce.backend.adpters.consumers.exceptions;

public class ZipNotFoundException extends RuntimeException {
    public ZipNotFoundException(String message) {
        super(message);
    }

    public ZipNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
