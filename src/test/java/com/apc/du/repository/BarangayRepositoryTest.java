package com.apc.du.repository;

import com.apc.du.commons.dto.DUDTO;
import com.apc.du.model.Barangay;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
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
        List<DUDTO> result = repository.getDUByBarangayCode("CEBCADL");
        assertThat(result).isInstanceOf(List.class);
    }

    @Test
    void success_getBarangayByCityBarangay() {
        List<DUDTO> result = repository.getDUByProvinceCityBarangay("Cebu","Cebu","Adlaon");
        assertThat(result).isInstanceOf(List.class);
    }
    private List<Barangay> getBarangay() {
        Barangay req = new Barangay("CEBCADL","Cebu City Adlaon","VECO", "CEB");
        List<Barangay> barangays = new ArrayList<>();
        barangays.add(req);
        return barangays;
    }
}