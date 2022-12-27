package com.apc.du.services.impl;

import com.apc.commons.response.BaseResponse;
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
    void success_getDUByBarangayCode() throws ServiceDisconnectedException {
        when(barangayRepository.getDUByBarangayCode("CEBCADL")).thenReturn(getDu());
        BaseResponse response = service.getDistributionUtility("Cebu","Cebu City", "Adlaon","CEBCADL");

        assertThat(response.getStatusCode()).isEqualTo(String.valueOf(APIResponse.SUCCESS.getCode()));
        assertThat(response.getMessage().toUpperCase()).isEqualTo(APIResponse.SUCCESS.getMessage());
        assertThat(response.getData()).isNotNull();
    }

    @Test
    void success_getDUByBrgyCityProvince() throws ServiceDisconnectedException {
        when(barangayRepository.getDUByProvinceCityBarangay("Cebu","Cebu City","Adlaon")).thenReturn(getDu());
        BaseResponse response = service.getDistributionUtility("Cebu", "Cebu City", "Adlaon", null);

        assertThat(response.getStatusCode()).isEqualTo(String.valueOf(APIResponse.SUCCESS.getCode()));
        assertThat(response.getMessage().toUpperCase()).isEqualTo(APIResponse.SUCCESS.getMessage());
        assertThat(response.getData()).isNotNull();
    }

    @Test
    void failure_404_getDistributionUtility() throws ServiceDisconnectedException {
        when(barangayRepository.getDUByBarangayCode("CEBCADL")).thenReturn(null);
        BaseResponse response = service.getDistributionUtility("Cebu", "Cebu City", "Adlaon", null);

        assertThat(response.getStatusCode().toString()).isEqualTo(String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()));
        assertThat(response.getMessage()).isEqualTo("Resource Not Found");
        assertThat(response.getData()).isNotNull();
    }

    @Test
    void failure_exception_getDistributionUtility() throws ServiceDisconnectedException {
        when(barangayRepository.getDUByBarangayCode("CEBCADL")).thenThrow(new RuntimeException());
        when(service.getDistributionUtility("CEBU","Cebu City", "Adlaon",null)).thenThrow(new RuntimeException());
    }

    private List<DistributionUtilityDTO> getDu() {
        DistributionUtilityDTO du = new DistributionUtilityDTO();
        du.setId(1L);
        du.setCode("VECO");
        du.setDescription("Visaya Electric");
        List<DistributionUtilityDTO> duList = new ArrayList<>();
        duList.add(du);
        return duList;
    }
}