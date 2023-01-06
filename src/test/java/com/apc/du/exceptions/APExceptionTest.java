package com.apc.du.exceptions;

import com.apc.commons.helper.DateHelper;
import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.config.GlobalExceptionHandler;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class APExceptionTest {
    @Spy
    @InjectMocks
    private GlobalExceptionHandler handler;

    HttpServletRequest httpServletRequest;

    BaseResponse<Map<String, String>> response = new BaseResponse<>();

    private final String STATUS = "STATUS";
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
    void handleUnexpectedExceptionAPException1() {
        APException exception = new APException(ERROR, MESSAGE, HttpStatus.CONFLICT);
        ResponseEntity<?> response =  handler.handleUnexpectedException(exception, httpServletRequest);

        System.out.print("RESPONSE >>> " + response.getBody());

        assertThat(response.getBody()).isExactlyInstanceOf(BaseResponse.class);
    }
}
