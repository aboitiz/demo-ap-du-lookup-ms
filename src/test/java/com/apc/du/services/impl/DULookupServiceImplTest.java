package com.apc.du.services.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.APIResponseDTO;
import com.apc.du.commons.dto.DistributionUtilityDTO;
import com.apc.du.commons.dto.LocationsDTO;
import com.apc.du.commons.dto.locations.*;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.repository.BarangayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class DULookupServiceImplTest {

    @Mock
    private BarangayRepository barangayRepository;

    @Spy
    @InjectMocks
    private DULookupServiceImpl service;

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
    void serviceDisconnected_getDULocationNoFilter() throws APException {
        try {
            doThrow(new IllegalStateException()).when(barangayRepository).getAllDULocations("", "", "", "", "");
            BaseResponse response = service.findAll("", "", "", "", "");
            System.out.println("RESPONSE : " + response);
            assertThat(response.getDataList()).isEmpty();
        } catch (Exception e) {
            assertThrows(APException.class, () -> service.findAll("", "", "", "", ""));
        }
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
