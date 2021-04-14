package com.example.dorywcza.model.offer;


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
public class SalaryTimeUnit {


    @Id
    @Column(name = "SALARY_TIME_UNIT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "salaryTimeUnit")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Salary> jobSalaries;

}

