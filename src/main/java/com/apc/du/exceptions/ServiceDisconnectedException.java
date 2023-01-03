package com.apc.du.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ServiceDisconnectedException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String code;
    private final HttpStatus httpStatus;

    public ServiceDisconnectedException(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ServiceDisconnectedException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
