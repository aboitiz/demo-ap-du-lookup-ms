package com.apc.du.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class APException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String code;
    private final HttpStatus httpStatus;

    public APException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        code = null;
    }

    public APException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
