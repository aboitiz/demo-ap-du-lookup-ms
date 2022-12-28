package com.apc.du.commons.model;

import com.apc.du.model.PostalCode;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class PostalCodeTest {
    @Test
    void success() {
        String code = "6000", description = "6000";
        PostalCode postalCode = new PostalCode();
        postalCode.setId(1L);
        postalCode.setCode(code);
        postalCode.setDescription(description);
        postalCode.setCreatedAt(new Date());
        postalCode.setModifiedAt(null);

        assertThat(postalCode.getId()).isNotNull();
        assertThat(postalCode.getCode()).isEqualTo(code);
        assertThat(postalCode.getDescription()).isEqualTo(description);
        assertThat(postalCode.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(postalCode.getModifiedAt()).isNull();
    }

    @Test
    void success_allArgs() {
        String code = "6000", description = "6000";
        Long id = 1L;
        PostalCode postalCode = new PostalCode(id, code, new HashSet<>(), description);

        assertThat(postalCode.getId()).isNotNull();
        assertThat(postalCode.getCode()).isEqualTo(code);
        assertThat(postalCode.getDescription()).isEqualTo(description);
    }
}
