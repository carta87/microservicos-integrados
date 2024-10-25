package com.microservice.course.exception.advice;

import com.library.entidades.dto.ErrorDTO;
import com.microservice.course.exception.exception.DuplicateNumberCourseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = DuplicateNumberCourseException.class)
    public ResponseEntity<ErrorDTO> duplicateNumberCourseExceptionHandler(DuplicateNumberCourseException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code("P-400")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(errorDTO);
    }
}
