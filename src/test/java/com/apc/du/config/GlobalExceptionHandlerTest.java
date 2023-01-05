package com.apc.du.config;

import com.apc.commons.helper.DateHelper;
import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GlobalExceptionHandlerTest {

    @Spy
    @InjectMocks
    private GlobalExceptionHandler handler;

    HttpServletRequest httpServletRequest;

    BaseResponse<Map<String, String>> response = new BaseResponse<>();

    private final String STATUS = "status";
    private final String ERROR = "ERROR";
    private final String MESSAGE = "Something went wrong";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        httpServletRequest = mock(HttpServletRequest.class);


        response.setMessage(MESSAGE);
        response.setStatusCode(STATUS);
        response.setTimestamp(DateHelper.getLocalDateTimeNow().toString());
        Map<String, String> data = new HashMap<>();
        data.put("error", MESSAGE);
        response.setData(data);
    }

    @Test
    void getTimestamp() throws APException {
        when(handler.getTimestamp())
                .thenReturn(DateHelper.getCurrentTimestamp().toString());
        assertThat(handler.getTimestamp()).isNotNull();
    }

    @Test
    void buildErrorResponse() {
        when(handler.buildErrorResponse(STATUS, APIResponse.SUCCESS, httpServletRequest, HttpStatus.OK))
                .thenReturn(null);
        assertThat(handler.buildErrorResponse(STATUS, APIResponse.SUCCESS, httpServletRequest, HttpStatus.OK))
                .isNull();
    }


    @Test
    void handleUnexpectedExceptionAPException() throws APException {
        APException exception = new APException(ERROR, HttpStatus.CONFLICT);
        ResponseEntity<?> response =
                handler.handleUnexpectedException(exception, httpServletRequest);
        assertThat(response.getBody()).isExactlyInstanceOf(BaseResponse.class);
    }

    @Test
    void handleUnexpectedExceptionAPException1() throws APException {
        APException exception = new APException(ERROR, MESSAGE, HttpStatus.CONFLICT);
        ResponseEntity<?> response =
                handler.handleUnexpectedException(exception, httpServletRequest);
        assertThat(response.getBody()).isExactlyInstanceOf(BaseResponse.class);
    }

    @Test
    void handleServiceDisconnectedException() {
        ServiceDisconnectedException serviceDisconnectedException = new ServiceDisconnectedException("ERROR",HttpStatus.CONFLICT);
        ResponseEntity<?> response =
                handler.handleServiceDisconnectedException(serviceDisconnectedException, httpServletRequest);
        assertThat(response.getBody()).isExactlyInstanceOf(BaseResponse.class);

    }

    @Test
    void handleServiceDisconnectedException1() {
        ServiceDisconnectedException serviceDisconnectedException = new ServiceDisconnectedException("ERROR",MESSAGE,HttpStatus.CONFLICT);
        ResponseEntity<?> response =
                handler.handleServiceDisconnectedException(serviceDisconnectedException, httpServletRequest);
        assertThat(response.getBody()).isExactlyInstanceOf(BaseResponse.class);

    }
}