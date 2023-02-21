package com.apc.du.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDTO {
    @ApiModelProperty(notes = "Id of the city", example = "1")
    Long id;

    @ApiModelProperty(notes = "Unique code of the city", example = "CEB")
    @Size(min = 2, message = "Code should have at least 2 characters")
    private String code;

    @ApiModelProperty(notes = "Description about the city", example = "relatively permanent and highly organized centre of population")
    @Size(min = 2, message = "Description should have at least 2 characters")
    private String description;

    @ApiModelProperty(position = 0, notes = "The id of the province where the city belongs to", example = "1")
    private Long provinceId;

    @ApiModelProperty(position = 1, notes = "The id of the postal code where the city belongs to", example = "8800")
    private Long postalCodeId;
}
