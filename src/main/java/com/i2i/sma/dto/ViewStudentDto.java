package com.i2i.sma.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * This class is responsible for managing the view student details that contains
 * 1.student id
 * 2.student name
 * 3.date of birth and
 * 4.student age
 * </p>
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ViewStudentDto {

    private UUID id;
    private String name;
    private LocalDate dob;
    private int age;
}

