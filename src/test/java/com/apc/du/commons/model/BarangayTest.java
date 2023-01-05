package com.apc.du.commons.model;

import com.apc.du.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class BarangayTest {
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

        assertThat(brgy.getCode()).isEqualTo(code);
        assertThat(brgy.getDescription()).isEqualTo(description);
        assertThat(brgy.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(brgy.getModifiedAt()).isNull();
        assertThat(brgy.getCity().getCode()).isEqualTo(city.getCode());
        assertThat(brgy.getDistributionUtility().getCode()).isEqualTo(distributionUtility.getCode());
    }

    @Test
    void success_equals() {
        String code = "CEBADL", description = "Adlaon";
        Barangay brgy = new Barangay();
        brgy.setCode(code);
        brgy.setDescription(description);

        Barangay brgy2 = new Barangay();
        brgy2.setCode(code);
        brgy2.setDescription(description);

        assertThat(brgy.equals(brgy2)).isTrue();
        assertThat(brgy.hashCode()).isEqualTo(brgy2.hashCode());
    }

    private Province getProvince(String code, String description) {
        return new Province(code, description, new HashSet<City>());
    }

    private PostalCode getPostalCode(String code, String description) {
        return new PostalCode(code, description, new HashSet<>());
    }

    private City getCity(String code, String description, Province province, PostalCode postalCode) {
        return new City("Carmen", "Carmen", province, postalCode, new ArrayList<>());
    }

    private DistributionUtility getDistributionUtility(String code, String description) {
        return new DistributionUtility(code, description, new HashSet<>());
    }
}
