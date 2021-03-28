package com.example.dorywcza.model;

import com.example.dorywcza.model.job_offer.JobOffer;
import com.example.dorywcza.model.job_offer.JobSalary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SALARY_TIME_UNIT")
public class SalaryTimeUnit {


    @Id
    @Column(name = "SALARY_TIME_UNIT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "salaryTimeUnit", cascade = CascadeType.ALL)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<JobSalary> jobSalaries;


}

