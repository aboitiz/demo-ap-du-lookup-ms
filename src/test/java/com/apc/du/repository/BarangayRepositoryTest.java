package com.apc.du.repository;

import com.apc.du.commons.dto.APIResponseDTO;
import com.apc.du.commons.dto.DistributionUtilityDTO;
import com.apc.du.model.Barangay;
import com.apc.du.model.City;
import com.apc.du.model.DistributionUtility;
import com.apc.du.model.Province;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BarangayRepositoryTest {

    @Autowired
    private BarangayRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(getBarangay().get(0));
    }

    @AfterEach
    void tearDown() {
        repository.delete(getBarangay().get(0));
    }

    @Test
    void success_getBarangayByCode() {
        List<APIResponseDTO> result = repository.getDUByBarangayCode("CEBCADL");
        assertThat(result.size()).isNotEqualTo(0);
    }

    @Test
    void success_getBarangayByProvinceCityBarangay() {
        List<APIResponseDTO> result = repository.getDUByProvinceCityBarangay("Cebu","Cebu City","Adlaon");
        assertThat(result.size()).isNotEqualTo(0);
    }

    @Test
    void failure_getBarangayByCode() {
        List<APIResponseDTO> result = repository.getDUByBarangayCode("ERR");
        assertThat(result.size()).isEqualTo(0);
    }

    private Province getProvince() {
        return new Province(1L, "CEB", "CEBU", new HashSet<>());
    }

    private City getCity() {
        return new City(1L, "CEB", "CEBU CITY", getProvince(), new HashSet<>());
    }

    private DistributionUtility getDistributionUtility() {
        return new DistributionUtility(1L, "VECO", "Visayan Electric", new HashSet<>());
    }

    private List<Barangay> getBarangay() {
        Barangay brgy = new Barangay();
        brgy.setCode("CEBADL");
        brgy.setDescription("Adlaon");
        brgy.setCity(getCity());
        brgy.setDistributionUtility(getDistributionUtility());
        List<Barangay> barangays = new ArrayList<>();

        barangays.add(brgy);
        return barangays;
    }
}
