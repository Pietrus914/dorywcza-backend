package com.example.dorywcza.model.offer;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long frequencyRating;


    @ManyToMany(mappedBy = "jobOfferTags")
//    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private List<JobOffer> jobOffer;

    public JobOfferTag(String name, Long frequencyRating) {
        this.name= name;
        this.frequencyRating = frequencyRating;
    }

    public void  increaseFrequencyRating(){
        frequencyRating++;
    }

    public void  decreaseFrequencyRating(){
        frequencyRating--;
    }

    public void addJobOffer(JobOffer addedJobOffer){
        jobOffer.add(addedJobOffer);
    }
}
