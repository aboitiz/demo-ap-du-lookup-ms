package com.apc.du.services.helpers;

import org.springframework.http.HttpStatus;

public class ResponseHelper {
    private ResponseHelper() {}

    public static HttpStatus getHttpStatus(String code) {
        HttpStatus status = null;
        switch (code) {
            case "200":
                status = HttpStatus.OK;
                break;
            case "201":
                status = HttpStatus.CREATED;
                break;
            case "400":
                status = HttpStatus.BAD_REQUEST;
                break;
            case "404":
                status = HttpStatus.NOT_FOUND;
                break;
            case "503":
                status = HttpStatus.SERVICE_UNAVAILABLE;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return status;
    }
}
