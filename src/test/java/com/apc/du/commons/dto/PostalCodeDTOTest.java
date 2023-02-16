package com.apc.du.commons.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PostalCodeDTOTest {
    @Test
    void success_postalCodeDTO() {
        Long id = 1L;
        String code = "TEST", description = "TEST";
        PostalCodeDTO dto = new PostalCodeDTO();
        dto.setId(1L);
        dto.setCode(code);
        dto.setDescription(description);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCode()).isEqualTo(code);
        assertThat(dto.getDescription()).isEqualTo(description);
    }
}
