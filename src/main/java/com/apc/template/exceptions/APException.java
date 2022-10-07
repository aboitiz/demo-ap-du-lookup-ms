package com.apc.template.exceptions;

import org.springframework.http.HttpStatus;

public class APException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;
    private final HttpStatus httpStatus;

    public APException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public APException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
