package br.edu.ifce.api.exceptions;

import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.exceptions.DataIntegrityException;
import br.edu.ifce.usecase.exceptions.EmailServiceException;
import br.edu.ifce.usecase.exceptions.InvalidConfirmationTokenException;
import br.edu.ifce.usecase.exceptions.InvalidEmailException;
import br.edu.ifce.usecase.exceptions.ObjectNotFoundException;
import br.edu.ifce.usecase.exceptions.ValidationException;
import br.edu.ifce.usecase.exceptions.ZipNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private StandardError getStandardError(RuntimeException exception,
                                           HttpServletRequest request,
                                           HttpStatus status) {
        return StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception,
                                                        HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException exception,
                                                       HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmailServiceException.class)
    public ResponseEntity<StandardError> emailServiceException(EmailServiceException exception,
                                                               HttpServletRequest request) {
        var status = HttpStatus.SERVICE_UNAVAILABLE;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<StandardError> invalidEmail(InvalidEmailException exception,
                                                      HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidConfirmationTokenException.class)
    public ResponseEntity<StandardError> invalidToken(InvalidConfirmationTokenException exception,
                                                      HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> forbidden(AuthorizationException exception,
                                                   HttpServletRequest request) {
        var status = HttpStatus.FORBIDDEN;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardError> validationException(ValidationException exception,
                                                             HttpServletRequest request) {
        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ZipNotFoundException.class)
    public ResponseEntity<StandardError> zipNotFound(ZipNotFoundException exception,
                                                     HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }
}
