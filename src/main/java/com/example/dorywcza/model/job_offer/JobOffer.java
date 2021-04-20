package com.example.dorywcza.model.job_offer;


import com.example.dorywcza.model.offer.*;
import com.example.dorywcza.model.user.User;
import lombok.*;


import javax.persistence.*;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class JobOffer extends Offer {

    public JobOffer(String title, String description, User user, boolean hasExperience, OfferLocation offerLocation,
                    DateRange dateRange, Industry industry, Salary salary, OfferSchedule offerSchedule, List<JobOfferTag> jobOfferTags) {
        super(title,  description,  user,  hasExperience,  offerLocation, dateRange,  industry,  salary,  offerSchedule);
        this.jobOfferTags = jobOfferTags;
    }


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "job_offer_job_offer_tag",
            joinColumns = { @JoinColumn(name = "job_offer_id") },
            inverseJoinColumns = { @JoinColumn(name = "job_offer_tag_id") }
    )
    List<JobOfferTag> jobOfferTags;
}