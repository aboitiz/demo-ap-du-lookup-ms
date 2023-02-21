package com.apc.du.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostalCodeDTO {
    @ApiModelProperty(notes = "Id of the postal code", example = "1")
    Long id;

    @ApiModelProperty(notes = "Unique code of the postal code", example = "2200")
    @Size(min = 2, message = "Code should have at least 2 characters")
    private String code;

    @ApiModelProperty(notes = "Brief description about the postal code", example = "series of letters or digits or both, sometimes including spaces or punctuation, included in a postal address for the purpose of sorting mail")
    @Size(min = 2, message = "Description should have at least 2 characters")
    private String description;
}
