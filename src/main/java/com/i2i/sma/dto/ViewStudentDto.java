package com.i2i.sma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor

/**
 * This class is responsible for managing the view student details that contains
 * 1.student id
 * 2.student name
 * 3.date of birth and
 * 4.student age
 */
public class ViewStudentDto {

    private UUID id;
    private String name;
    private LocalDate dob;
    private int age;
}

