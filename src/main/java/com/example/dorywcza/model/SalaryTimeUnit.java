package com.example.dorywcza.model;

import com.example.dorywcza.model.job_offer.JobSalary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


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
//    @JsonManagedReference
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<JobSalary> jobSalaries;


}

