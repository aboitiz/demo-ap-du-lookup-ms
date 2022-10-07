package com.apc.template.commons.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseAPIResponse {

    private String timestamp;
    private String statusCode;
    private String message;
    private Object data;

}
