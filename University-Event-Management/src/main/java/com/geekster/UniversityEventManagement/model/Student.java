package com.geekster.UniversityEventManagement.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student")

public class Student {


    @Id
    private Integer studentId;
    private String studentFirstName;
    private String studentLastName;

    @Min(value = 18)
    @Max(value = 30)
    private Integer age;
    private Type StudentDepartment;


}


