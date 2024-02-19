package com.example.cinemahall_rest_copy.cinema.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ShowAlreadyExistsException extends RuntimeException {
    public ShowAlreadyExistsException(String message) {
        super(message);
    }
}
