package com.i2i.sma.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class RequestStudentDto {

    private String name;
    private LocalDate dob;
    private RequestGradeDto grade;
}
