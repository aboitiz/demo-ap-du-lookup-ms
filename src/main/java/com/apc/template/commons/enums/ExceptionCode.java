package com.apc.template.commons.enums;

public enum ExceptionCode {

    UNEXPECTED_EXCEPTION("100","Unexpected Error");

    private final String code;
    private final String description;

    ExceptionCode(String code, String description){
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
