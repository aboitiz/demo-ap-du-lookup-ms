package com.apc.du.controller;

import com.apc.du.commons.constants.APIPathConstants;
import com.apc.du.services.impl.DULookupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    void getRequirements() throws Exception {
        when(service.getDistributionUtility(any(), any(), any(), any())).thenReturn(null);
        mockMvc.perform(
                        get(url() + "/du")
                                .param("province","Cebu")
                                .param("city","Cebu City")
                                .param("barangay","Adlaon")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    private String url() {
        return APIPathConstants.API_VERSION_1 + APIPathConstants.DU_BASE_PATH;
    }

}