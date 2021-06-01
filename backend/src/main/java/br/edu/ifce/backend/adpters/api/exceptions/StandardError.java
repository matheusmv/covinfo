package br.edu.ifce.backend.adpters.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {
    private Integer status;
    private String message;
    private Instant timestamp;
}
