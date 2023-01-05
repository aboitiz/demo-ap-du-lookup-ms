package com.apc.du.commons.model;

import com.apc.du.model.Barangay;
import com.apc.du.model.DistributionUtility;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DistributionUtilityTest {
    @Test
    void success() {
        String code = "VECO", description = "Visayan Electric";
        Set<Barangay> brgys = new HashSet<>();
        brgys.add(new Barangay());
        DistributionUtility du = new DistributionUtility();
        du.setId(1L);
        du.setCode(code);
        du.setDescription(description);
        du.setCreatedAt(new Date());
        du.setModifiedAt(null);
        du.setBarangays(brgys);

        assertThat(du.getCode()).isEqualTo(code);
        assertThat(du.getDescription()).isEqualTo(description);
        assertThat(du.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(du.getModifiedAt()).isNull();
        assertThat(du.getBarangays().stream().toList().get(0)).isInstanceOf(Barangay.class);
    }
}
