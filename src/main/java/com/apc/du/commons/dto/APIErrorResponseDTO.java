package com.apc.du.commons.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class APIErrorResponseDTO {


    private String error;

    public APIErrorResponseDTO() {

    }

    public APIErrorResponseDTO(String error) {
        super();
        this.error = error;
    }

}
