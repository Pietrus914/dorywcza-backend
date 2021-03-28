package com.example.dorywcza.model.job_offer;

import com.example.dorywcza.model.SalaryTimeUnit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Data
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "JOB_SALARY")
public class JobSalary {

    @Id
    @Column(name = "JOB_SALARY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long minSalary;
    private Long maxSalary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SALARY_TIME_UNIT_ID")
    @JsonBackReference
    private SalaryTimeUnit salaryTimeUnit;


    @OneToOne(mappedBy = "jobSalary")
    @JsonIgnore
    private JobOffer jobOffer;


}
