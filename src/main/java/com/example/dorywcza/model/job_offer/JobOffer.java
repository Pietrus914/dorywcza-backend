package com.example.dorywcza.model.job_offer;


import com.example.dorywcza.model.offer.Offer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;



@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class JobOffer extends Offer {



}