package com.example.dorywcza.model.job_offer;

import com.example.dorywcza.model.Industry;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "JOB_OFFER")
@SecondaryTable(name = "DATES_RANGE",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "JOB_OFFER_ID", referencedColumnName = "JOB_OFFER_ID"))
    public class JobOffer {

    @Id
    @Column(name = "JOB_OFFER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;

//    Date when  JobOffer is created or updated is automatically updated in database

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="CET")
    private Date dateCreated;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="CET")
    private Date dateUpdated;

    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdated = new Date();
    }

//    Job start & end date stored in DATES_RANGE table
    @Column(name="START_DATE", table = "DATES_RANGE")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="CET")
    private Date startDate;
    @Column(name="END_DATE", table = "DATES_RANGE")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="CET")
    private Date endDate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INDUSTRY_ID")
    @EqualsAndHashCode.Exclude
    private Industry industry;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "JOB_SALARY_ID")
    @EqualsAndHashCode.Exclude
    private JobSalary jobSalary;


}