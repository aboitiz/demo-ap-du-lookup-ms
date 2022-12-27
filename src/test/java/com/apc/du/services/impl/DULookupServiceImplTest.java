package com.apc.du.services.impl;

import com.apc.du.commons.dto.DistributionUtilityDTO;
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


class DULookupServiceImplTest {

    @Mock
    private BarangayRepository barangayRepository;

    @Spy
    @InjectMocks
    private DULookupServiceImpl service;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void success_getDUBarangayCode() throws ServiceDisconnectedException {
        when(barangayRepository.getDUByBarangayCode("CEBCADL")).thenReturn(getDu());
        when(service.getDistributionUtility("CEBU","Cebu City", "Adlaon","CEBCADL")).thenReturn(null);
    }

    @Test
    void success_getDUCityBrngy()  throws ServiceDisconnectedException {
        when(barangayRepository.getDUByProvinceCityBarangay("Cebu","Cebu","Adlaon")).thenReturn(getDu());
        when(service.getDistributionUtility("CEBU","Cebu City", "Adlaon",null)).thenReturn(null);
    }

    @Test
    void success_getDUCityBrngy_with_cityCheck()  throws ServiceDisconnectedException {
        when(barangayRepository.getDUByProvinceCityBarangay("Cebu","Cebu","Adlaon")).thenReturn(getDu());
        when(service.getDistributionUtility("CEBU","Cebu", "Adlaon",null)).thenReturn(null);
    }

    @Test
    void returnsNull_getDu()  throws ServiceDisconnectedException {
        when(barangayRepository.getDUByBarangayCode("CEBCADL")).thenReturn(null);
        when(service.getDistributionUtility("CEBU","Cebu City", "Adlaon",null)).thenReturn(null);
    }

    @Test
    void runOnException_getDu()  throws ServiceDisconnectedException {
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