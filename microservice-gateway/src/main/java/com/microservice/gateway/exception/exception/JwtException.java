package com.microservice.gateway.exception.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class JwtException extends RuntimeException {

    private final HttpStatus httpStatus;

    public JwtException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
