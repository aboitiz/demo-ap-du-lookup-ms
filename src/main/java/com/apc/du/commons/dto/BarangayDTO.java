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
public class BarangayDTO {
    @ApiModelProperty(notes = "Id of the barangay", example = "1")
    Long id;

    @ApiModelProperty(notes = "The barangay code", example = "ABCD")
    @Size(min = 2, message = "Code should have at least 2 characters")
    private String code;

    @ApiModelProperty(notes = "Description about the barangay", example = "A barangay is the smallest political unit in the country")
    @Size(min = 2, message = "Description should have at least 2 characters")
    private String description;

    @ApiModelProperty(position = 0, notes = "The id of distribution utility where the barangay belongs to", example = "1")
    private Long distributionUtilityId;

    @ApiModelProperty(position = 1, notes = "The id of city where the barangay belongs to", example = "1")
    private Long cityId;
}
