package com.apc.du.commons.model;

import com.apc.du.model.City;
import com.apc.du.model.Province;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ProvinceTest {
    @Test
    void success() {
        String code = "CEB", description = "Cebu City";
        Set<City> cities = new HashSet<>();
        cities.add(new City());
        Province province = new Province();
        province.setId(1L);
        province.setCode(code);
        province.setDescription(description);
        province.setCreatedAt(new Date());
        province.setModifiedAt(null);
        province.setCities(cities);

        assertThat(province.getCode()).isEqualTo(code);
        assertThat(province.getDescription()).isEqualTo(description);
        assertThat(province.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(province.getCities().stream().toList().get(0)).isInstanceOf(City.class);
    }

    @Test
    void success_equals() {
        String code = "CEB", description = "Cebu City";
        Province province = new Province();
        province.setCode(code);
        province.setDescription(description);

        Province province2  = new Province();
        province2.setCode(code);
        province2.setDescription(description);

        assertThat(province.equals(province2)).isTrue();
        assertThat(province.hashCode()).isEqualTo(province2.hashCode());
    }
}
