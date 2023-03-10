package com.apc.du.config;

import com.apc.commons.helper.DateHelper;
import com.apc.commons.response.BaseResponse;
import com.apc.du.commons.dto.APIErrorResponseDTO;
import com.apc.du.commons.enums.APIResponse;
import com.apc.du.exceptions.APException;
import com.apc.du.exceptions.ServiceDisconnectedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Generates timestamp of error log
     *
     * @return
     * @throws APException
     */
    protected String getTimestamp() {

        /**
         * SET format and timezone to default;
         */
        return DateHelper.getCurrentTimestamp().toString();
    }

    /**
     * Builds error response based on exception thrown
     *
     * @param statusCode
     * @param apiResponse
     * @param httpReq
     * @param httpStatus
     * @return
     */
    protected ResponseEntity<BaseResponse<APIErrorResponseDTO>> buildErrorResponse(String statusCode, APIResponse apiResponse,
                                                                 HttpServletRequest httpReq, HttpStatus httpStatus) {
        BaseResponse<APIErrorResponseDTO> errResponse = new BaseResponse<>();
        errResponse.setTimestamp(getTimestamp());
        errResponse.setStatusCode(statusCode);
        errResponse.setMessage(apiResponse.getMessage());
        errResponse.setData(APIErrorResponseDTO.builder().error(apiResponse.getDescription()).build());

        return new ResponseEntity<>(errResponse, httpStatus);
    }

    /**
     * Generic error response for internal server error
     *
     * @param ex
     * @param httpReq
     * @return
     */
    @ExceptionHandler(APException.class)
    public ResponseEntity<BaseResponse<APIErrorResponseDTO>> handleUnexpectedException(Exception ex, HttpServletRequest httpReq) {
        log.error(APIResponse.GENERAL_DECLINE.getDescription(), ex);
        return buildErrorResponse(String.valueOf(APIResponse.GENERAL_DECLINE.getCode()), APIResponse.GENERAL_DECLINE,
                httpReq, HttpStatus.BAD_REQUEST);
    }

    /**
     * Error response for Service Disconnected
     *
     * @param ex
     * @param httpReq
     * @return
     */
    @ExceptionHandler(ServiceDisconnectedException.class)
    public ResponseEntity<BaseResponse<APIErrorResponseDTO>> handleServiceDisconnectedException(Exception ex, HttpServletRequest httpReq) {
        log.error(APIResponse.SERVICE_DISCONNECTED.getDescription(), ex);
        return buildErrorResponse(String.valueOf(APIResponse.SERVICE_DISCONNECTED.getCode()),
                APIResponse.SERVICE_DISCONNECTED, httpReq, HttpStatus.REQUEST_TIMEOUT);
    }
}
