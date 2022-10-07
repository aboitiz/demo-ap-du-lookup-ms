package com.apc.template.config;

import com.apc.template.commons.dto.BaseAPIErrorResponse;
import com.apc.template.commons.enums.APIResponse;
import com.apc.template.exceptions.APException;
import com.apc.template.services.helpers.DateTimeHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Generates timestamp of error log
     * @return
     * @throws APException
     */
    protected String getTimestamp() throws APException {

        /**
         * SET format and timezone to default;
         */
        return DateTimeHelper.formatDateToTimestamp(new Date(), null, null);
    }

    /**
     * Builds error response based on exception thrown
     * @param statusCode
     * @param apiResponse
     * @param httpReq
     * @param httpStatus
     * @return
     */
    protected ResponseEntity<BaseAPIErrorResponse> buildErrorResponse(String statusCode, APIResponse apiResponse,
                                                                               HttpServletRequest httpReq, HttpStatus httpStatus) {

        BaseAPIErrorResponse errResponse = new BaseAPIErrorResponse();

        try {
            errResponse.setTimestamp(getTimestamp());
            errResponse.setStatusCode(statusCode);
            errResponse.setMessage(apiResponse.getMessage());
            errResponse.setData(httpReq.getRequestURI());
            errResponse.setError(apiResponse.getError());

        } catch (APException e) {
            log.debug(e.getMessage(), e);
            buildErrorResponse(String.valueOf(apiResponse.getCode()), apiResponse, httpReq, httpStatus);
        }
        return new ResponseEntity<>(errResponse, httpStatus);
    }

    /**
     * Generic error response for internal server error
     * @param ex
     * @param httpReq
     * @return
     */
    @ExceptionHandler(APException.class)
    public ResponseEntity<BaseAPIErrorResponse> handleUnexpectedException(Exception ex, HttpServletRequest httpReq) {
        log.error(APIResponse.GENERAL_DECLINE.getDescription(), ex);
        return buildErrorResponse(String.valueOf(APIResponse.GENERAL_DECLINE.getCode()), APIResponse.GENERAL_DECLINE,
                httpReq, HttpStatus.BAD_REQUEST);
    }
}
