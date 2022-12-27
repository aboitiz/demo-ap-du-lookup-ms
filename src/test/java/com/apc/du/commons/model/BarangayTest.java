package com.apc.du.commons.model;

import com.apc.du.model.Barangay;
import com.apc.du.model.City;
import com.apc.du.model.DistributionUtility;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class BarangayTest {
    @Test
    void success() {
        String code = "CEBADL", description = "Adlaon";
        Barangay brgy = new Barangay();
        brgy.setId(1L);
        brgy.setCode(code);
        brgy.setDescription(description);
        brgy.setCreatedAt(new Date());
        brgy.setModifiedAt(null);
        brgy.setCity(new City());
        brgy.setDistributionUtility(new DistributionUtility());

        assertThat(brgy.getId()).isNotNull();
        assertThat(brgy.getCode()).isEqualTo(code);
        assertThat(brgy.getDescription()).isEqualTo(description);
        assertThat(brgy.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(brgy.getModifiedAt()).isNull();
        assertThat(brgy.getCity()).isInstanceOf(City.class);
        assertThat(brgy.getDistributionUtility()).isInstanceOf(DistributionUtility.class);
    }
}
