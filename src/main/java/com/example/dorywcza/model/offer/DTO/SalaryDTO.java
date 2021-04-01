package com.example.dorywcza.model.offer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalaryDTO {
    private Long minSalary;
    private Long maxSalary;
}
