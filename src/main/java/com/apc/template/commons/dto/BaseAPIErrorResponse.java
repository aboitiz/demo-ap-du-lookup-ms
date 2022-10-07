package com.apc.template.commons.dto;

public class BaseAPIErrorResponse extends BaseAPIResponse {


    private String error;

    public BaseAPIErrorResponse() {

    }

    public BaseAPIErrorResponse(String timestamp, String status, String error, String message, Object data) {
        super();
        super.setTimestamp(timestamp);
        super.setStatusCode(status);
        this.error = error;
        super.setMessage(message);
        super.setData(data);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
