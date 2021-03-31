package com.example.dorywcza.model.job_offer;


import com.example.dorywcza.model.offer.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;



@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class JobOffer extends Offer {

    public JobOffer(String title, String description, Long userId, boolean hasExperience, OfferLocation offerLocation,
                 DateRange dateRange, Industry industry, Salary salary, OfferSchedule offerSchedule) {
        super(title,  description,  userId,  hasExperience,  offerLocation, dateRange,  industry,  salary,  offerSchedule);
    }

}