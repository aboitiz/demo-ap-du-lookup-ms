package com.apc.du.commons.enums;

import lombok.Getter;

@Getter
public enum APIResponse {


    SUCCESS(200,"","SUCCESS","The request was successful and the response body contains the representation requested."),
    SUCCESS_CREATED(201,"","CREATED","The request has been fulfilled, resulting in the creation of a new resource"),
    GENERAL_DECLINE(500,"UNEXPECTED_ERROR","An internal server error occurred. Please contact your administrator.","The request was failed due to unexpected error"),
    APPLICATION_STATUS_NOT_FOUND(404,"APPLICATION_STATUS_NOT_FOUND", "Service Application Not Found.","The request cannot be completed as the application status Reference ID not found."),
    SERVICE_DISCONNECTED(408, "SERVICE_DISCONNECTED", "Service Disconnected", "The REST API service or server indicated is unreachable.");


    private final int code;
    private final String error;
    private final String message;
    private final String description;

    APIResponse(int code, String error, String message, String description){
        this.code = code;
        this.error = error;
        this.message = message;
        this.description = description;
    }
}
