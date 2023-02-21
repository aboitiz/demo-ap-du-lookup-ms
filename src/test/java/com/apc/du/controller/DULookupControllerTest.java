package com.apc.du.controller;

import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.constants.APIPathConstants;
import com.apc.du.commons.dto.BarangayDTO;
import com.apc.du.commons.dto.CityDTO;
import com.apc.du.commons.dto.PostalCodeDTO;
import com.apc.du.commons.dto.ProvinceDTO;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import com.apc.du.services.DULookupService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DULookupController.class)
class DULookupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DULookupService service;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void success_create_postal_code() throws Exception {
        when(service.postalCode(any())).thenReturn(new BaseResponse<>().setStatusCode(String.valueOf(HttpStatus.CREATED.value())));
        mockMvc.perform(post(url() + "/postalCode")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new PostalCodeDTO()))
        ).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void success_update_postal_code() throws Exception {
        PostalCodeDTO update = PostalCodeDTO.builder().id(1L).build();
        when(service.postalCode(any(), any())).thenReturn(new BaseResponse<>().setStatusCode(String.valueOf(HttpStatus.OK.value())));
        mockMvc.perform(post(url() + "/postalCode")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void success_create_province() throws Exception {
        when(service.province(any())).thenReturn(new BaseResponse<>().setStatusCode(String.valueOf(HttpStatus.CREATED.value())));
        mockMvc.perform(post(url() + "/province")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProvinceDTO()))
        ).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void success_update_province() throws Exception {
        ProvinceDTO update = ProvinceDTO.builder().id(1L).build();
        when(service.province(any(), any())).thenReturn(new BaseResponse<>().setStatusCode(String.valueOf(HttpStatus.OK.value())));
        mockMvc.perform(post(url() + "/province")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void success_create_city() throws Exception {
        when(service.city(any())).thenReturn(new BaseResponse<>().setStatusCode(String.valueOf(HttpStatus.CREATED.value())));
        mockMvc.perform(post(url() + "/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new CityDTO()))
        ).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void success_update_city() throws Exception {
        CityDTO update = CityDTO.builder().id(1L).build();
        when(service.city(any(), any())).thenReturn(new BaseResponse<>().setStatusCode(String.valueOf(HttpStatus.OK.value())));
        mockMvc.perform(post(url() + "/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void success_create_barangay() throws Exception {
        when(service.barangay(any())).thenReturn(new BaseResponse<>().setStatusCode(String.valueOf(HttpStatus.CREATED.value())));
        mockMvc.perform(post(url() + "/barangay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new BarangayDTO()))
        ).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void success_update_barangay() throws Exception {
        BarangayDTO update = BarangayDTO.builder().id(1L).build();
        when(service.barangay(any(), any())).thenReturn(new BaseResponse<>().setStatusCode(String.valueOf(HttpStatus.OK.value())));
        mockMvc.perform(post(url() + "/barangay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update))
        ).andDo(print()).andExpect(status().isOk());
    }

    private String url() {
        return APIPathConstants.API_VERSION_1 + APIPathConstants.DU_BASE_PATH;
    }
}