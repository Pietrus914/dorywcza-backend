package com.example.dorywcza.model.user.DTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@ApiModel(description = "DTO - abstract class")
@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class UserDTO {

}
