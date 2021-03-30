package com.example.dorywcza.model.offer;

import com.example.dorywcza.model.Industry;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int userId;
    private boolean hasExperience;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date dateCreated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date dateUpdated;


    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdated = new Date();
    }

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private OfferLocation offerLocation;


    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private DateRange dateRange;

    @ManyToOne
    @JoinColumn(name = "INDUSTRY_ID")
    @EqualsAndHashCode.Exclude
    private Industry industry;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SALARY_ID")
    @EqualsAndHashCode.Exclude
    private Salary salary;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private OfferSchedule offerSchedule;


    public Offer(String title, String description, int userId, boolean hasExperience, OfferLocation offerLocation,
                 DateRange dateRange, Industry industry,  Salary salary, OfferSchedule offerSchedule) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.hasExperience = hasExperience;
        this.salary = salary;
        this.offerLocation = offerLocation;
        this.dateRange = dateRange;
        this.industry = industry;
        this.offerSchedule = offerSchedule;

    }
}
