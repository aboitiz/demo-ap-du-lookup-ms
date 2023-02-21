package com.apc.du.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProvinceDTO {
    @ApiModelProperty(notes = "Unique code for the province", example = "CEBU")
    @Size(min = 2, message = "Code should have at least 2 characters")
    private String code;

    @ApiModelProperty(notes = "Description about the province", example = " province is an area of land that is part of a country, similar to a state or a county")
    @Size(min = 2, message = "Description should have at least 2 characters")
    private String description;
}

