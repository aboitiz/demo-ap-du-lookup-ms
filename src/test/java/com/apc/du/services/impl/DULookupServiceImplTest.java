package com.apc.du.services.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.APIResponseDTO;
import com.apc.du.commons.dto.DistributionUtilityDTO;
import com.apc.du.commons.dto.LocationsDTO;
import com.apc.du.commons.dto.PostalCodeDTO;
import com.apc.du.commons.dto.locations.*;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.repository.BarangayRepository;
import com.apc.du.repository.CityRepository;
import com.apc.du.repository.PostalCodeRepository;
import com.apc.du.repository.ProvinceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class DULookupServiceImplTest {

    @Mock
    private BarangayRepository barangayRepository;

    @Mock
    private PostalCodeRepository postalCodeRepository;

    @Mock
    private ProvinceRepository provinceRepository;

    @Mock
    private CityRepository cityRepository;

    @Spy
    @InjectMocks
    private DULookupServiceImpl service;

    private PostalCodeDTO postalCodeDTO = PostalCodeDTO
            .builder()
            .code("123")
            .description("123")
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void success_getDUByBrgyCityProvincePostalCode() throws ServiceDisconnectedException {
        when(barangayRepository.getDUByProvinceCityBarangayPostalCode("Cebu", "Cebu City", "Adlaon", "1"))
                .thenReturn(getDu());
        BaseResponse response = service.getDistributionUtility("Cebu", "Cebu City", "Adlaon", "1");

        assertThat(response.getStatusCode()).isEqualTo(String.valueOf(APIResponse.SUCCESS.getCode()));
        assertThat(response.getMessage().toUpperCase()).isEqualTo(APIResponse.SUCCESS.getMessage());
        assertThat(response.getData()).isNotNull();
    }

    @Test
    void failure_getDUByBrgyCityProvincePostalCode() throws ServiceDisconnectedException {
        when(barangayRepository.getDUByProvinceCityBarangayPostalCode("Cebu", "Cebu City", "Adlaon", "1"))
                .thenReturn(new ArrayList<>());
        BaseResponse response = service.getDistributionUtility("Cebu", "Cebu City", "Adlaon", "1");

        assertThat(response.getStatusCode()).isEqualTo(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()));
        assertThat(response.getDataList()).isEmpty();
    }

    @Test
    void success_getDULocationNoFilter() throws APException {
        when(barangayRepository.getAllDULocations("", "", "", "", ""))
                .thenReturn(getDULocations());
        BaseResponse res = service.findAll("", "", "", "", "");

        assertThat(res.getStatusCode()).isEqualTo(String.valueOf(APIResponse.SUCCESS.getCode()));
        assertThat(res.getMessage().toUpperCase()).isEqualTo(APIResponse.SUCCESS.getMessage());
        assertThat(res.getData()).isNotNull();

    }

    @Test
    void success_getDULocationWithFilter() throws APException {
        when(barangayRepository.getAllDULocations("VECO", "DAVAO DEL NORTE", "Dujali", "8105DUJ", "Dujali Magupising")).thenReturn(getDULocations());
        when(service.findAll("VECO", "DAVAO DEL NORTE", "Dujali", "8105DUJ", "Dujali Magupising"))
                .thenReturn(new BaseResponse<>(getDULocations()).setStatusCodeMessage(String.valueOf(HttpStatus.OK.value()), "Success"));
        assertThat(service.findAll("VECO", "DAVAO DEL NORTE", "Dujali", "8105DUJ", "Dujali Magupising")).isExactlyInstanceOf(BaseResponse.class);

    }

    @Test
    void notFound_getDULocationNoFilter() throws APException {
        when(barangayRepository.getAllDULocations("", "", "", "", "")).thenReturn(new ArrayList<>());
        BaseResponse res = service.findAll("", "", "", "", "");

        assertThat(res.getStatusCode()).isEqualTo(String.valueOf(APIResponse.DU_LOCATION_NOT_FOUND.getCode()));
        assertThat(res.getMessage()).isEqualTo(APIResponse.DU_LOCATION_NOT_FOUND.getDescription());
        assertThat(res.getDataList()).isEmpty();

    }

    @Test
    void serviceDisconnected_getDULocationNoFilter() {
        try {
            doThrow(new IllegalStateException()).when(barangayRepository).getAllDULocations("", "", "", "", "");
            BaseResponse response = service.findAll("", "", "", "", "");
            assertThat(response.getDataList()).isEmpty();
        } catch (Exception e) {
            assertThrows(APException.class, () -> service.findAll("", "", "", "", ""));
        }
    }

    @Test
    void success_create_postalCode() throws APException {
        when(postalCodeRepository.countByCode(any())).thenReturn(0L);
        BaseResponse result = service.postalCode(postalCodeDTO);

        assertThat(result.getStatusCode()).isEqualTo(String.valueOf(HttpStatus.CREATED.value()));
    }

    @Test
    void failure_create_postalCode_existing() throws APException {
        when(postalCodeRepository.countByCode(any())).thenReturn(1L);
        BaseResponse result = service.postalCode(postalCodeDTO);

        assertThat(result.getStatusCode()).isEqualTo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void failure_create_postalCode_database() throws APException {

    }

    @Test
    void success_update_postalCode() throws APException, IllegalAccessException {
        when(postalCodeRepository.findById(any())).thenReturn(any());
        BaseResponse result = service.postalCode(postalCodeDTO, Long.valueOf(1));

//        assertThat(result.getStatusCode()).isEqualTo(String.valueOf(HttpStatus.OK.value()));
    }

    @Test
    void failure_update_postalCode_invalid_id() {

    }

    @Test
    void failure_update_postalCode_database() {

    }

    @Test
    void success_create_province() {

    }

    @Test
    void failure_create_province_existing() {

    }

    @Test
    void failure_create_province_database() {

    }

    @Test
    void success_update_province() {

    }

    @Test
    void failure_update_province_invalid_id() {

    }

    @Test
    void failure_update_province_database() {

    }

    @Test
    void success_create_city() {

    }

    @Test
    void failure_create_city_missing_ids() {

    }

    @Test
    void failure_create_city_invalid_ids() {

    }

    @Test
    void failure_create_city_existing() {

    }

    @Test
    void failure_create_city_database() {

    }

    @Test
    void success_update_city() {

    }

    @Test
    void failure_update_city_invalid_ids() {

    }

    @Test
    void failure_update_city_invalid_city() {

    }

    @Test
    void failure_update_city_database() {

    }

    private List<APIResponseDTO> getDu() {
        List<APIResponseDTO> dus = new ArrayList<>();
        APIResponseDTO response = new APIResponseDTO();
        response.setBarangay("Adlaon");
        response.setCity("Cebu City");
        response.setProvince("Cebu");
        List<DistributionUtilityDTO> duList = new ArrayList<>();
        DistributionUtilityDTO du = new DistributionUtilityDTO();
        du.setId(1L);
        du.setCode("VECO");
        du.setDescription("Visayan Electric");
        duList.add(du);

        response.setDistributionUtility(du);

        dus.add(response);
        return dus;
    }

    private List<LocationsDTO> getDULocations() {
        List<LocationsDTO> locations = new ArrayList<>();

        DistributionUtility distributionUtility = new DistributionUtility(2L, "VECO", "Visayan Electric");
        Province province = new Province(1L, "DAVN", "DAVAO DEL NORTE");
        City city = new City(6L, "DUJ", "Dujali");
        PostalCode postalCode = new PostalCode(16L, "8105DUJ", "Braulio E. Dujali");
        Barangay barangay = new Barangay(623L, "DUJMAG", "Dujali Magupising");

        LocationsDTO l = new LocationsDTO(province, city, postalCode, barangay, distributionUtility);

        locations.add(l);

        return locations;
    }
}
