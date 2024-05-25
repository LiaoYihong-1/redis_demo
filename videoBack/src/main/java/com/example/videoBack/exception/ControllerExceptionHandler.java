package com.example.videoBack.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice @Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        log.info("ResourceNotFoundException");
        Error message = new Error(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);

    }
    @ExceptionHandler(value = {InvalidParameterException.class})
    public ResponseEntity<?> invalidParameterException(InvalidParameterException ex, WebRequest request) {
        log.info("InvalidParameterException");
        Error message = new Error(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);

    }

    @ExceptionHandler(value = {InvalidConditionException.class})
    public ResponseEntity<?> invalidConditionException(InvalidConditionException ex, WebRequest request) {
        log.info("InvalidConditionException");
        Error message = new Error(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);

    }
    @ExceptionHandler(value = {javax.validation.ConstraintViolationException.class})
    public ResponseEntity<?> constraintViolationException(javax.validation.ConstraintViolationException ex, WebRequest request) {
        log.info("ConstraintViolationException");
        Error message = new Error(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);

    }
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<?> constraintViolationException(IllegalArgumentException ex, WebRequest request) {
        log.info("IllegalArgumentException");
        Error message = new Error(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);

    }
    @ExceptionHandler(value = {org.springframework.http.converter.HttpMessageNotReadableException.class})
    public ResponseEntity<?> constraintViolationException(org.springframework.http.converter.HttpMessageNotReadableException ex, WebRequest request) {
        log.info("HttpMessageNotReadableException");
        Error message = new Error(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);

    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> constraintViolationException(MethodArgumentNotValidException ex, WebRequest request) {
        log.info("MethodArgumentTypeMismatchException");
        Error message = new Error(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);
    }
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<?> constraintViolationException(UsernameNotFoundException ex, WebRequest request) {
        Error message = new Error(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);
    }
    @ExceptionHandler(value = {JsonProcessingException.class})
    public ResponseEntity<?> constraintViolationException(JsonProcessingException ex, WebRequest request) {
        Error message = new Error(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);
    }

}
