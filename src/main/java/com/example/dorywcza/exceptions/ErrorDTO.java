package com.example.dorywcza.exceptions;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "DTO with information about exception: message and level.\nLevels values: INFO, WARN, ERROR")
@Data
@NoArgsConstructor
public class ErrorDTO {
    @ApiModelProperty(required = true, value = "Message for user that is carried by the exception.")
    private String message;
    @ApiModelProperty(value = "Level specifies the kind of error. Levels values: INFO, WARN, ERROR.\n")
    private String level;

    public ErrorDTO(String message, ExceptionLevels level) {
        this.message = message;
        this.level = level.toString();
    }
}
