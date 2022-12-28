package com.apc.du.commons.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class APIResponseDTO {
    private String province;
    private String city;
    private String barangay;
    private DistributionUtilityDTO distributionUtility;

    public APIResponseDTO(
            String province,
            String city,
            String barangay,
            Long duId,
            String duCode,
            String duDescription
    ) {
        this.province = province;
        this.city = city;
        this.barangay = barangay;
        this.distributionUtility = new DistributionUtilityDTO(duId, duCode, duDescription);
    }
}