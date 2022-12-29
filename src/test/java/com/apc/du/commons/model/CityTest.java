package com.apc.du.commons.model;

import com.apc.du.model.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CityTest {
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

        assertThat(city.getId()).isNotNull();
        assertThat(city.getCode()).isEqualTo(code);
        assertThat(city.getDescription()).isEqualTo(description);
        assertThat(city.getProvince().getCode()).isEqualTo(province.getCode());
        assertThat(city.getPostalCode().getCode()).isEqualTo(postalCode.getCode());
        assertThat(city.getBarangays().stream().toList().get(0).getCity().getCode()).isEqualTo(city.getCode());
        assertThat(city.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(city.getModifiedAt()).isNull();
    }

    private Province getProvince(String code, String description) {
        return new Province(1L, code, description, new HashSet<City>());
    }

    private PostalCode getPostalCode(String code, String description) {
        return new PostalCode(1L, code, description, new HashSet<>());
    }

    private List<Barangay> getBarangays(City city) {
        List<Barangay> brgys = new ArrayList<>();
        Barangay brgy = new Barangay(1L, "CEBADL", "Adlaon", city, new DistributionUtility());
        brgys.add(brgy);

        return brgys;
    }

    private DistributionUtility getDistributionUtility(String code, String description) {
        return new DistributionUtility(1L, code, description, new HashSet<>());
    }
}
