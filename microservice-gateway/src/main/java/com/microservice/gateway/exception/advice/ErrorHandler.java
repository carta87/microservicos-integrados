package com.microservice.gateway.exception.advice;

import com.microservice.gateway.dto.ErrorDTO;
import com.microservice.gateway.exception.exception.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> runtimeEceptionHandler(RuntimeException exception) {
        ErrorDTO error = ErrorDTO.builder()
                .code("P-400")
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<ErrorDTO> jwtExceptionHandler(JwtException exception) {
        ErrorDTO error = ErrorDTO.builder()
                .code("P-401")
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(error, exception.getHttpStatus());
    }
}
