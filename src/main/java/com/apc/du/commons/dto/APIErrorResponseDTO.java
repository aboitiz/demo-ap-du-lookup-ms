package com.apc.du.commons.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIErrorResponseDTO {
    private String error;
}
