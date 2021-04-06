package com.example.dorywcza.model.job_offer;


import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.offer.DTO.JobOfferTagDTO;
import com.example.dorywcza.model.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class JobOffer extends Offer {

    public JobOffer(String title, String description, User user, boolean hasExperience, OfferLocation offerLocation,
                    DateRange dateRange, Industry industry, Salary salary, OfferSchedule offerSchedule) {
        super(title,  description,  user,  hasExperience,  offerLocation, dateRange,  industry,  salary,  offerSchedule);
    }


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "job_offer_job_offer_tag",
            joinColumns = { @JoinColumn(name = "job_offer_id") },
            inverseJoinColumns = { @JoinColumn(name = "job_offer_tag_id") }
    )
    List<JobOfferTag> jobOfferTags;

}