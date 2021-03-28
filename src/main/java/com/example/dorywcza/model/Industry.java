package com.example.dorywcza.model;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "INDUSTRY")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Industry {

    @Id
    @Column(name = "INDUSTRY_ID")
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany(mappedBy = "industry")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<JobOffer> jobOffers;

}


