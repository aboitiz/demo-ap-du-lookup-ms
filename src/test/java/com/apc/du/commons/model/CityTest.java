package com.apc.du.commons.model;

import com.apc.du.model.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class CityTest {
    @Test
    void success() {
        String code = "CEB", description = "Cebu";
        Province province = getProvince(code, description);
        PostalCode postalCode = getPostalCode("8101", "8101");

        City city = new City();
        city.setId(1L);
        city.setCode(code);
        city.setDescription(description);
        city.setProvince(province);
        city.setPostalCode(postalCode);
        city.setCreatedAt(new Date());
        city.setModifiedAt(null);

        List<Barangay> brgys = getBarangays(city);
        city.setBarangays(brgys);

        assertThat(city.getCode()).isEqualTo(code);
        assertThat(city.getDescription()).isEqualTo(description);
        assertThat(city.getProvince().getCode()).isEqualTo(province.getCode());
        assertThat(city.getPostalCode().getCode()).isEqualTo(postalCode.getCode());
        assertThat(city.getBarangays().stream().toList().get(0).getCity().getCode()).isEqualTo(city.getCode());
        assertThat(city.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(city.getModifiedAt()).isNull();
    }

    @Test
    void success_equals() {
        String code = "CEB", description = "Cebu";
        City city = new City();
        city.setCode(code);
        city.setDescription(description);

        City city2 = new City();
        city.setCode(code);
        city.setDescription(description);

        assertThat(city.equals(city2)).isTrue();
        assertThat(city.hashCode()).isEqualTo(city2.hashCode());
    }

    private Province getProvince(String code, String description) {
        return new Province(code, description, new HashSet<City>());
    }

    private PostalCode getPostalCode(String code, String description) {
        return new PostalCode(code, description, new HashSet<>());
    }

    private List<Barangay> getBarangays(City city) {
        List<Barangay> brgys = new ArrayList<>();
        Barangay brgy = new Barangay("CEBADL", "Adlaon", city, new DistributionUtility());
        brgys.add(brgy);

        return brgys;
    }

    private DistributionUtility getDistributionUtility(String code, String description) {
        return new DistributionUtility(code, description, new HashSet<>());
    }
}
