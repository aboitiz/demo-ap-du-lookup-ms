package com.apc.du.commons.advice;

import com.apc.du.commons.enums.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        errorMap.put("status", String.valueOf(APIResponse.APPLICATION_STATUS_NOT_FOUND.getCode()));
        errorMap.put("message", "Validation Failed");
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("errors", errors);
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return errorMap;
    }
}
