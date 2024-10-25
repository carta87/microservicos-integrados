package com.microservice.course.exception.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class DuplicateNumberCourseException extends RuntimeException{

    private final HttpStatus httpStatus;

    public DuplicateNumberCourseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
