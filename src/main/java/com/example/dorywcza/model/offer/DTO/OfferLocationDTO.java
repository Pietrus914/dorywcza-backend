package com.example.dorywcza.model.offer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfferLocationDTO {
    private double xPosition;
    private double yPosition;
    private String cityName;
}
