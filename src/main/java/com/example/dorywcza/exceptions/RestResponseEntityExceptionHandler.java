package com.example.dorywcza.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RecordNotFound.class})
    protected  ResponseEntity<ErrorDTO> handle(RecordNotFound ex, WebRequest request){

        String message = ex.getMessage() + " not found";
        ErrorDTO errorDTO = new ErrorDTO(message, ExceptionLevels.INFO);
        return ResponseEntity.status(ex.getStatusCode()).body(errorDTO);
    }

    @ExceptionHandler(value = {ParameterNotValid.class})
    protected  ResponseEntity<ErrorDTO> handle(ParameterNotValid ex){

        String message = ex.getMessage();
        ErrorDTO errorDTO = new ErrorDTO(message, ExceptionLevels.WARN);
        return ResponseEntity.status(ex.getStatusCode()).body(errorDTO);
    }

    @Override
    protected  ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        String message = ex.getMessage();
        ErrorDTO errorDTO = new ErrorDTO(message, ExceptionLevels.WARN);
        return ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {

        final String message = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ErrorDTO errorDTO = new ErrorDTO(message, ExceptionLevels.WARN);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }
}
