package com.i2i.sma.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ResponseStudentDto {

    private int id;
    private String name;
    private LocalDate dob;
    private int age;
    private ResponseGradeDto grade;

}
