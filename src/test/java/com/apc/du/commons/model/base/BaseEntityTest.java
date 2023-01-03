package com.apc.du.commons.model.base;

import com.apc.du.model.base.BaseEntity;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseEntityTest {
    @Test
    void success() {
        Long id = 1L;
        BaseEntity base = new BaseEntity();
        base.setId(id);
        base.setCreatedAt(new Date());

        assertThat(base.getId()).isEqualTo(id);
        assertThat(base.getCreatedAt()).isInstanceOf(Date.class);
    }
}
