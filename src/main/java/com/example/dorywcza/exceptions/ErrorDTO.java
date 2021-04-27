package com.example.dorywcza.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDTO {
    private String message;
    private String level;

    public ErrorDTO(String message, ExceptionLevels level) {
        this.message = message;
        this.level = level.toString();
    }
}
