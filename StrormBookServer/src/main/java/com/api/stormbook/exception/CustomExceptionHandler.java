package com.api.stormbook.exception;

import com.api.stormbook.entity.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//@RestControllerAdvice
@ControllerAdvice
public class CustomExceptionHandler {
//    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorResponse handleNotFoundException(NotFoundException e, WebRequest req) {
//        return new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
//    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Object>> handleNotFound(NotFoundException ex, WebRequest request) {
       ApiResponse<Object> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
