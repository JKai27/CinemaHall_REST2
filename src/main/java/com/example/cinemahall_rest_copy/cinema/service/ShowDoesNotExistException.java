package com.example.cinemahall_rest_copy.cinema.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ShowDoesNotExistException extends RuntimeException {
    public ShowDoesNotExistException(String message) {
        super(message);
    }
}
