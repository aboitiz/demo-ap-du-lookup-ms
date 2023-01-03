package com.apc.du.commons.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class APIErrorResponseDTOTest {
    @Test
    void success_apiErrorResponseDTO() {
        APIErrorResponseDTO dto = new APIErrorResponseDTO();
        dto.setError("Error");

        assertThat(dto.getError()).isEqualTo("Error");
    }
}
