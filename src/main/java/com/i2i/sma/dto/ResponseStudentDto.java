package com.i2i.sma.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter

/**
 * This class is responsible for managing the response student details that contains
 * 1.student id
 * 2.student name
 * 3.date of birth
 * 4.student age
 * 5.response grade {@link ResponseGradeDto}
 */
public class ResponseStudentDto {

    private UUID id;
    private String name;
    private LocalDate dob;
    private int age;
    private ResponseGradeDto grade;

}
