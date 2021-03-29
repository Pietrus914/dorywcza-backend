package com.example.dorywcza.model.service_offer;


import com.example.dorywcza.model.Industry;
import com.example.dorywcza.model.offer.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class ServiceOffer extends Offer {



    public ServiceOffer(String title, String description, int userId, boolean hasExperience,
                        OfferLocation offerLocation, DateRange dateRange,
                        Salary salary, OfferSchedule offerSchedule) {
        super(title, description, userId, hasExperience, offerLocation, dateRange,  salary, offerSchedule);

    }
}
