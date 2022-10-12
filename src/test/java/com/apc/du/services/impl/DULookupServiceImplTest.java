package com.apc.du.services.impl;

import com.apc.du.commons.dto.DUDTO;
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
    void success_getDUBarangayCode() {
        when(barangayRepository.getDUByBarangayCode("CEBCADL")).thenReturn(getDu());
        when(service.getDUByCityBarangay("CEBU","Cebu City", "Adlaon","CEBCADL")).thenReturn(null);
    }

    @Test
    void success_getDUCityBrngy() {
        when(barangayRepository.getDUByProvinceCityBarangay("Cebu","Cebu","Adlaon")).thenReturn(getDu());
        when(service.getDUByCityBarangay("CEBU","Cebu City", "Adlaon",null)).thenReturn(null);
    }

    @Test
    void success_getDUCityBrngy_with_cityCheck() {
        when(barangayRepository.getDUByProvinceCityBarangay("Cebu","Cebu","Adlaon")).thenReturn(getDu());
        when(service.getDUByCityBarangay("CEBU","Cebu", "Adlaon",null)).thenReturn(null);
    }

    @Test
    void returnsNull_getDu() {
        when(barangayRepository.getDUByBarangayCode("CEBCADL")).thenReturn(null);
        when(service.getDUByCityBarangay("CEBU","Cebu City", "Adlaon",null)).thenReturn(null);
    }

    @Test
    void runOnException_getDu() {
        when(barangayRepository.getDUByBarangayCode("CEBCADL")).thenThrow(new RuntimeException());
        when(service.getDUByCityBarangay("CEBU","Cebu City", "Adlaon",null)).thenThrow(new RuntimeException());

    }


    private List<DUDTO> getDu() {
        DUDTO du = new DUDTO();
        du.setDu("VECO");
        du.setProvince("Cebu");
        du.setCity("Cebu");
        du.setBarangay("Adlaon");
        du.setBarangayCode("CEBCADL");
        List<DUDTO> duList = new ArrayList<>();
        duList.add(du);
        return duList;
    }
}