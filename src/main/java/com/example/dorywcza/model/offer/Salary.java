package com.example.dorywcza.model.offer;


import com.example.dorywcza.model.SalaryTimeUnit;
import lombok.*;

import javax.persistence.*;

@Data
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Salary {

    @Id
    @Column(name = "SALARY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long minSalary;
    private Long maxSalary;

    @ManyToOne
    @JoinColumn(name = "SALARY_TIME_UNIT_ID")
    private SalaryTimeUnit salaryTimeUnit;

}
