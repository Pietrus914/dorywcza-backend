package com.example.dorywcza.model.offer;

import com.example.dorywcza.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
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


    public Offer(String title, String description, User user, boolean hasExperience, OfferLocation offerLocation,
                 DateRange dateRange, Industry industry, Salary salary, OfferSchedule offerSchedule) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.hasExperience = hasExperience;
        this.salary = salary;
        this.offerLocation = offerLocation;
        this.dateRange = dateRange;
        this.industry = industry;
        this.offerSchedule = offerSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return hasExperience == offer.hasExperience && Objects.equals(title, offer.title) &&
                Objects.equals(description, offer.description) &&
                Objects.equals(user, offer.user) && Objects.equals(offerLocation, offer.offerLocation) &&
                Objects.equals(dateRange, offer.dateRange) &&
                Objects.equals(industry, offer.industry) &&
                Objects.equals(salary, offer.salary) &&
                Objects.equals(offerSchedule, offer.offerSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, user, hasExperience, offerLocation, dateRange, industry, salary, offerSchedule);
    }
}
