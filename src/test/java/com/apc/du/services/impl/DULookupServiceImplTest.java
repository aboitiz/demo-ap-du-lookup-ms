package com.apc.du.services.impl;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.APIResponseDTO;
import com.apc.du.commons.dto.DistributionUtilityDTO;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.repository.BarangayRepository;
import com.apc.du.services.impl.impl.DULookupServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

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
        when(barangayRepository.getDUByProvinceCityBarangayPostalCode("Cebu","Cebu City","Adlaon", "1")).thenReturn(getDu());
        BaseResponse response = service.getDistributionUtility("Cebu", "Cebu City", "Adlaon", "1");

        assertThat(response.getStatusCode()).isEqualTo(String.valueOf(APIResponse.SUCCESS.getCode()));
        assertThat(response.getMessage().toUpperCase()).isEqualTo(APIResponse.SUCCESS.getMessage());
        assertThat(response.getData()).isNotNull();
    }

    @Test
    void failure_getDUByBrgyCityProvincePostalCode() throws ServiceDisconnectedException {
        when(barangayRepository.getDUByProvinceCityBarangayPostalCode("Cebu","Cebu City","Adlaon", "1")).thenReturn(new ArrayList<>());
        BaseResponse response = service.getDistributionUtility("Cebu", "Cebu City", "Adlaon", "1");

        assertThat(response.getStatusCode()).isEqualTo(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()));
        assertThat(response.getDataList()).isEmpty();
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
}
