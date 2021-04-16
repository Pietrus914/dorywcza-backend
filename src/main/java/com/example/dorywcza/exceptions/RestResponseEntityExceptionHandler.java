package com.example.dorywcza.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RecordNotFound.class})
    protected  ResponseEntity<ErrorDTO> handle(RecordNotFound ex, WebRequest request){
        String message = ex.getMessage() + " not found";
        ErrorDTO errorDTO = new ErrorDTO(ex.getStatusCode(), message, ExceptionLevels.INFO);
        return ResponseEntity.status(ex.getStatusCode()).body(errorDTO);
    }
}
