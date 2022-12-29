package com.apc.du.commons.model;

import com.apc.du.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class BarangayTest {
    @Test
    void success() {
        String code = "CEBADL", description = "Adlaon";
        Province province = getProvince("Cebu", "Cebu");
        PostalCode postalCode = getPostalCode("8101", "8101");
        City city = getCity("Carmen", "Carmen", province, postalCode);
        DistributionUtility distributionUtility = getDistributionUtility( "VECO", "Visayan Electric");

        Barangay brgy = new Barangay();
        brgy.setId(1L);
        brgy.setCode(code);
        brgy.setDescription(description);
        brgy.setCreatedAt(new Date());
        brgy.setModifiedAt(null);
        brgy.setCity(city);
        brgy.setDistributionUtility(distributionUtility);

        assertThat(brgy.getId()).isNotNull();
        assertThat(brgy.getCode()).isEqualTo(code);
        assertThat(brgy.getDescription()).isEqualTo(description);
        assertThat(brgy.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(brgy.getModifiedAt()).isNull();
        assertThat(brgy.getCity().getCode()).isEqualTo(city.getCode());
        assertThat(brgy.getDistributionUtility().getCode()).isEqualTo(distributionUtility.getCode());
    }

    private Province getProvince(String code, String description) {
        return new Province(1L, code, description, new HashSet<City>());
    }

    private PostalCode getPostalCode(String code, String description) {
        return new PostalCode(1L, code, description, new HashSet<>());
    }

    private City getCity(String code, String description, Province province, PostalCode postalCode) {
        return new City(1L, "Carmen", "Carmen", province, postalCode, new ArrayList<>());
    }

    private DistributionUtility getDistributionUtility(String code, String description) {
        return new DistributionUtility(1L, code, description, new HashSet<>());
    }
}
