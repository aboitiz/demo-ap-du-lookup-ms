package com.apc.du.commons.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CityDTO {
    private Long id;

    @Size(min = 2, message = "Code should have at least 2 characters")
    private String code;

    @Size(min = 2, message = "Description should have at least 2 characters")
    private String description;

    private String provinceId;

    private String postalCodeId;
}
