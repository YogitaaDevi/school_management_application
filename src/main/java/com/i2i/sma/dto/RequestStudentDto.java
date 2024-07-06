package com.i2i.sma.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter

/**
 * This class is responsible for managing the requested student details that contains
 * 1.student name
 * 2.date of birth and
 * 3.requested grade {@link RequestGradeDto}
 */
public class RequestStudentDto {

    private String name;
    private LocalDate dob;
    private RequestGradeDto grade;
}
