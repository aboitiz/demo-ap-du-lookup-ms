//package com.apc.du.repository;
//
//import com.apc.du.commons.dto.APIResponseDTO;
//import com.apc.du.model.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class BarangayRepositoryTest {
//
//    @Autowired
//    private BarangayRepository barangayRepository;
//
//    @Autowired
//    private PostalCodeRepository postalCodeRepository;
//
//    @Autowired
//    private CityRepository cityRepository;
//
//    @BeforeEach
//    void setUp() {
//        postalCodeRepository.save(getPostalCode());
//        barangayRepository.save(getBarangay().get(0));
//    }
//
//    @Test
//    void contextLoads() {
//        assertThat(cityRepository).isNotNull();
//        assertThat(postalCodeRepository).isNotNull();
//        assertThat(barangayRepository).isNotNull();
//    }
//
//    @AfterEach
//    void tearDown() {
//        postalCodeRepository.delete(getPostalCode());
//        barangayRepository.delete(getBarangay().get(0));
//    }
//
//    @Test
//    void success_getBarangayByProvinceCityBarangay() {
//        List<APIResponseDTO> result = barangayRepository.getDUByProvinceCityBarangayPostalCode("Cebu","Cebu City","Adlaon", "1");
//        assertThat(result.size()).isNotEqualTo(0);
//    }
//
//    private PostalCode getPostalCode() {
//        return PostalCode.builder()
//                .id(1L)
//                .code("1")
//                .description("1")
//                .cities(new HashSet<>())
//                .build();
//    }
//
//    private Province getProvince() {
//        return Province.builder()
//                .id(1L)
//                .code("CEB")
//                .description("CEBU")
//                .cities(new HashSet<>())
//                .build();
//    }
//
//    private City getCity() {
//        return City.builder()
//                .id(1L)
//                .code("CEB")
//                .description("CEBU CITY")
//                .province(getProvince())
//                .postalCode(getPostalCode())
//                .barangays(new ArrayList<>())
//                .build();
//    }
//
//    private DistributionUtility getDistributionUtility() {
//        return DistributionUtility.builder()
//                .id(1L)
//                .code("VECO")
//                .description("Visayan Electric")
//                .barangays(new HashSet<>())
//                .build();
//    }
//
//    private List<Barangay> getBarangay() {
//        Barangay brgy = new Barangay();
//        brgy.setCode("CEBADL");
//        brgy.setDescription("Adlaon");
//        brgy.setCity(getCity());
//        brgy.setDistributionUtility(getDistributionUtility());
//        List<Barangay> barangays = new ArrayList<>();
//
//        barangays.add(brgy);
//        return barangays;
//    }
//}
