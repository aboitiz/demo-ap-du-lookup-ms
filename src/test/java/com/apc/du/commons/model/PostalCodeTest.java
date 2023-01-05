package com.apc.du.commons.model;

import com.apc.du.model.City;
import com.apc.du.model.PostalCode;
import com.apc.du.model.Province;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class PostalCodeTest {

    @Test
    void success() {
        String code = "6000", description = "6000";
        PostalCode postalCode = new PostalCode();
        Set<City> cities = getCities();

        postalCode.setId(1L);
        postalCode.setCode(code);
        postalCode.setCities(cities);
        postalCode.setDescription(description);
        postalCode.setCreatedAt(new Date());
        postalCode.setModifiedAt(null);

        assertThat(postalCode.getId()).isNotNull();
        assertThat(postalCode.getCode()).isEqualTo(code);
        assertThat(postalCode.getDescription()).isEqualTo(description);
        assertThat(postalCode.getCities().stream().toList().get(0).getCode()).isEqualTo(cities.stream().toList().get(0).getCode());
        assertThat(postalCode.getCreatedAt()).isInstanceOf(Date.class);
        assertThat(postalCode.getModifiedAt()).isNull();
    }

    @Test
    void success_allArgs() {
        String code = "6000", description = "6000";
        PostalCode postalCode = new PostalCode(code, description, new HashSet<>());

        assertThat(postalCode.getCode()).isEqualTo(code);
        assertThat(postalCode.getDescription()).isEqualTo(description);
    }

    @Test
    void success_equals() {
        String code = "6000", description = "6000";
        PostalCode postalCode = new PostalCode();
        postalCode.setCode(code);
        postalCode.setDescription(description);

        PostalCode postalCode2 = new PostalCode();
        postalCode2.setCode(code);
        postalCode2.setDescription(description);

        assertThat(postalCode.equals(postalCode2));
        assertThat(postalCode.hashCode() == postalCode2.hashCode());
    }


    private Set<City> getCities() {
        City city = new City("CEB", "CEBU", new Province(), new PostalCode(), new ArrayList<>());
        Set<City> cities = new HashSet<>();
        cities.add(city);

        return cities;
    }
}
