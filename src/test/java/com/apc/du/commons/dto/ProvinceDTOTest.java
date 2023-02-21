package com.apc.du.commons.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProvinceDTOTest {
    @Test
    void success_provinceCodeDTO() {
        Long id = 1L;
        String code = "TEST", description = "TEST";
        ProvinceDTO dto = new ProvinceDTO();
        dto.setCode(code);
        dto.setDescription(description);

        assertThat(dto.getCode()).isEqualTo(code);
        assertThat(dto.getDescription()).isEqualTo(description);
    }
}
