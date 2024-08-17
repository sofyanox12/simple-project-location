package com.codetalkz.projectlocator.api.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundExceptionHandler extends Exception {

    public NotFoundExceptionHandler(String message) {
        super(message);
    }
}
