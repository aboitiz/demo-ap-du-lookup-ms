package com.apc.du.commons.model.base;

import com.apc.du.model.base.AuditableEntity;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class AuditableEntityTest {
    @Test
    void success() {
        Long id = 1L;
        AuditableEntity model = new AuditableEntity();
        model.setId(id);
        model.setCreatedAt(new Date());
        model.setModifiedAt(null);

        assertThat(model.getId()).isEqualTo(id);
        assertThat(model.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(model.getModifiedAt()).isNull();
    }
}
