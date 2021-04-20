package com.example.dorywcza.model.service_offer;


import com.example.dorywcza.model.offer.Industry;
import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class ServiceOffer extends Offer {


    public ServiceOffer(String title, String description, User user, boolean hasExperience,
                        OfferLocation offerLocation, DateRange dateRange, Industry industry,
                        Salary salary, OfferSchedule offerSchedule, List<ServiceOfferTag> serviceOfferTags) {
        super(title, description, user, hasExperience, offerLocation, dateRange, industry,  salary, offerSchedule);
        this.serviceOfferTags = serviceOfferTags;

    }


    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "service_offer_service_offer_tag",
            joinColumns = { @JoinColumn(name = "service_offer_id") },
            inverseJoinColumns = { @JoinColumn(name = "service_offer_tag_id") }
    )
    List<ServiceOfferTag> serviceOfferTags;


}
