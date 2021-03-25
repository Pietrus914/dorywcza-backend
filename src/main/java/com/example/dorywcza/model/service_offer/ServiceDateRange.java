package com.example.dorywcza.model.service_offer;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
public class ServiceDateRange {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private Date startDate;
    private Date endDate;

    public ServiceDateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
