package com.example.dorywcza.model.offer;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "jobOfferTags")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private List<JobOffer> jobOffer;

}
