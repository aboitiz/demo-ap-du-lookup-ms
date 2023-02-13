package com.apc.du.controller;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.constants.APIPathConstants;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.services.DULookupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DULookupController.class)
class DULookupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DULookupService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void success_getDistributionUtilityByBarangayCityProvince() throws Exception {
        when(service.getDistributionUtility(any(), any(), any(), any())).thenReturn(null);
        mockMvc.perform(
                        get(url() + "/du")
                                .param("province", "Cebu")
                                .param("city", "Cebu City")
                                .param("barangay", "Adlaon")
                                .param("postalCode", "6001")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void success_getDistributionUtilityWithBarangayCode() throws Exception {
        when(service.getDistributionUtility(any(), any(), any(), any())).thenReturn(null);
        mockMvc.perform(
                        get(url() + "/du")
                                .param("province", "Cebu")
                                .param("city", "Cebu City")
                                .param("barangay", "Adlaon")
                                .param("postalCode", "6001")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void success_getAllDistributionUtilityNoFilter() throws Exception {
        when(service.findAll(any(), any(), any(), any(), any()))
                .thenReturn(new BaseResponse<>().setStatusCode(String.valueOf(HttpStatus.OK.value())));
        mockMvc.perform(
                        get(url() + "/locations")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void notFound_getAllDistributionUtilityNoFilter() throws Exception {
        when(service.findAll(any(), any(), any(), any(), any()))
                .thenReturn(new BaseResponse<>().setStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value())));
        mockMvc.perform(
                        get(url() + "/locations")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    void failed_getAllDistributionUtilityNoFilter() throws Exception {
        when(service.findAll(any(), any(), any(), any(), any()))
                .thenThrow(new APException(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()),
                        APIResponse.SERVICE_DISCONNECTED.toString(), HttpStatus.SERVICE_UNAVAILABLE));
        mockMvc.perform(
                        get(url() + "/locations")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof APException))
                .andReturn();

    }

    @Test
    void failure_getDistributionUtility() throws Exception {
        when(service.getDistributionUtility(any(), any(), any(), any())).thenThrow(ServiceDisconnectedException.class);
        mockMvc.perform(
                        get(url() + "/du")
                                .param("province", "asdasd")
                                .param("city", "asdasd")
                                .param("barangay", "err")
                                .param("postalCode", "123123")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    private String url() {
        return APIPathConstants.API_VERSION_1 + APIPathConstants.DU_BASE_PATH;
    }
}