package com.apc.du.repository;

import com.apc.du.commons.dto.APIResponseDTO;
import com.apc.du.model.*;
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
    private BarangayRepository barangayRepository;

    @Autowired
    private PostalCodeRepository postalCodeRepository;

    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    void setUp() {
        postalCodeRepository.save(getPostalCode());
        barangayRepository.save(getBarangay().get(0));
    }

    @AfterEach
    void tearDown() {
        postalCodeRepository.delete(getPostalCode());
        barangayRepository.delete(getBarangay().get(0));
    }

    @Test
    void success_getBarangayByProvinceCityBarangay() {
        List<APIResponseDTO> result = barangayRepository.getDUByProvinceCityBarangayPostalCode("Cebu","Cebu City","Adlaon", "1");
        assertThat(result.size()).isNotEqualTo(0);
    }

    private PostalCode getPostalCode() {
        return new PostalCode(1L, "1", "1", new HashSet<>());
    }

    private Province getProvince() {
        return new Province(1L, "CEB", "CEBU", new HashSet<>());
    }

    private City getCity() {
        return new City(1L, "CEB", "CEBU CITY", getProvince(), getPostalCode(), new ArrayList<>());
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
