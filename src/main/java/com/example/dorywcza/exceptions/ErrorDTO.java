package com.example.dorywcza.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorDTO {
    private HttpStatus status;
    private int httStatus;
    private String message;
    private String level;
    private List<String> errors;

    public ErrorDTO(HttpStatus status, String message, ExceptionLevels level, String error) {
        this.status = status;
        this.httStatus = status.value();
        this.message = message;
        this.level = level.toString();
        this.errors = Arrays.asList(error);
    }

    public ErrorDTO(HttpStatus status, String message, ExceptionLevels level, List<String> errors){
        this.status = status;
        this.httStatus = status.value();
        this.message = message;
        this.level = level.toString();
        this.errors = errors;
    }

    public ErrorDTO(HttpStatus status, String message, ExceptionLevels level) {
        this.status = status;
        this.httStatus = status.value();
        this.message = message;
        this.level = level.toString();
    }
}
