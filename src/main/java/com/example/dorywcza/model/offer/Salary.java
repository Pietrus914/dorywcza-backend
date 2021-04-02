package com.example.dorywcza.model.offer;


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

    public Salary(Long minSalary, Long maxSalary) {
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }
}
