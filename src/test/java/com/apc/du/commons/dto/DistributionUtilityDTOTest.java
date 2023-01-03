package com.apc.du.commons.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DistributionUtilityDTOTest {
    @Test
    void success_createDto() {
        String code = "VECO", description = "Visayan Electric";
        DistributionUtilityDTO du = new DistributionUtilityDTO();
        du.setId(1L);
        du.setCode(code);
        du.setDescription(description);

        assertThat(du.getId()).isEqualTo(1L);
        assertThat(du.getCode()).isEqualTo(code);
        assertThat(du.getDescription()).isEqualTo(description);
    }
}
