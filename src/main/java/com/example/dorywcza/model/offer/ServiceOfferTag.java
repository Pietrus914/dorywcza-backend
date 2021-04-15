package com.example.dorywcza.model.offer;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.service_offer.ServiceOffer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOfferTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long frequencyRating;


    @ManyToMany(mappedBy = "serviceOfferTags")
//    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private List<ServiceOffer> serviceOffer;

    public ServiceOfferTag(String name, Long frequencyRating) {
        this.name= name;
        this.frequencyRating = frequencyRating;
    }

    public void  increaseFrequencyRating(){
        frequencyRating++;
    }

    public void  decreaseFrequencyRating(){
        frequencyRating--;
    }

    public void addJobOffer(ServiceOffer addedServiceOffer){
        serviceOffer.add(addedServiceOffer);
    }
}
