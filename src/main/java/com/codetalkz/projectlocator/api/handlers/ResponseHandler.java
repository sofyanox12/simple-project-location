package com.codetalkz.projectlocator.api.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    private ResponseHandler() {

    }

    public static ResponseEntity<Object> give(Object data, String message, HttpStatus status) {

        Map<String, Object> responseBody = new HashMap<>();

        responseBody.put("data", data);

        responseBody.put("message", message);

        return new ResponseEntity<>(responseBody, status);
    }
}
