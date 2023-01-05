package com.apc.du.commons.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class APIResponseDTOTest {
    @Test
    void success_apiResponseDTO() {
        String province = "Cebu", city = "Carmen", barangay = "Alejal", postalCode = "8101";
        DistributionUtilityDTO duDTO = new DistributionUtilityDTO(1L, "VECO", "Visayan Electric");
        APIResponseDTO dto = new APIResponseDTO();
        dto.setProvince(province);
        dto.setCity(city);
        dto.setBarangay(barangay);
        dto.setPostalCode(postalCode);
        dto.setDistributionUtility(duDTO);

        assertThat(dto.getProvince()).isEqualTo(province);
        assertThat(dto.getCity()).isEqualTo(city);
        assertThat(dto.getBarangay()).isEqualTo(barangay);
        assertThat(dto.getPostalCode()).isEqualTo(postalCode);
        assertThat(dto.getDistributionUtility().getCode()).isEqualTo(duDTO.getCode());
        assertThat(dto.getDistributionUtility().getDescription()).isEqualTo(duDTO.getDescription());
    }

    @Test
    void success_allArgsConstructor() {
        String province = "Cebu", city = "Carmen", barangay = "Alejal", postalCode = "8101";
        DistributionUtilityDTO duDTO = new DistributionUtilityDTO(1L, "VECO", "Visayan Electric");
        APIResponseDTO dto = new APIResponseDTO(province, city, postalCode, barangay, 1L, duDTO.getCode(), duDTO.getDescription());

        assertThat(dto.getProvince()).isEqualTo(province);
        assertThat(dto.getCity()).isEqualTo(city);
        assertThat(dto.getBarangay()).isEqualTo(barangay);
        assertThat(dto.getPostalCode()).isEqualTo(postalCode);
        assertThat(dto.getDistributionUtility().getCode()).isEqualTo(duDTO.getCode());
        assertThat(dto.getDistributionUtility().getDescription()).isEqualTo(duDTO.getDescription());
    }
}
