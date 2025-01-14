package com.example.videoBack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidParameterException(String msg) {
        super(msg);
    }

}