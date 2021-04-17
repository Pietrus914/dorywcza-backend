package com.example.dorywcza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ParameterNotValid extends HttpClientErrorException {

    public ParameterNotValid(HttpStatus statusCode, String message){
        super(statusCode, message);
    }
}
