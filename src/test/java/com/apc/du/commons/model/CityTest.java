package com.apc.du.commons.model;

import com.apc.du.model.Barangay;
import com.apc.du.model.City;
import com.apc.du.model.Province;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CityTest {
    @Test
    void success() {
        String code = "CEB", description = "Cebu";
        Set<Barangay> brgys = new HashSet<>();
        brgys.add(new Barangay());

        City city = new City();
        city.setId(1L);
        city.setCode(code);
        city.setDescription(description);
        city.setProvince(new Province());
        city.setBarangays(brgys);
        city.setCreatedAt(new Date());
        city.setModifiedAt(null);

        assertThat(city.getId()).isNotNull();
        assertThat(city.getCode()).isEqualTo(code);
        assertThat(city.getDescription()).isEqualTo(description);
        assertThat(city.getProvince()).isInstanceOf(Province.class);
        assertThat(city.getBarangays().stream().toList().get(0)).isInstanceOf(Barangay.class);
        assertThat(city.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(city.getModifiedAt()).isNull();
    }
}
