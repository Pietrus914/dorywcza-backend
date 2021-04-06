package com.example.dorywcza.model.service_offer;


import com.example.dorywcza.model.offer.Industry;
import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class ServiceOffer extends Offer {



    public ServiceOffer(String title, String description, User user, boolean hasExperience,
                        OfferLocation offerLocation, DateRange dateRange, Industry industry,
                        Salary salary, OfferSchedule offerSchedule) {
        super(title, description, user, hasExperience, offerLocation, dateRange, industry,  salary, offerSchedule);

    }
}
