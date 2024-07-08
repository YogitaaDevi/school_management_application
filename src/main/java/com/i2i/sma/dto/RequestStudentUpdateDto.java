package com.i2i.sma.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * This class is responsible for managing the requested student details to update that contains
 * 1.student name
 * 2.date of birth
 * </p>
 */

@Builder
@Getter
@Setter
public class RequestStudentUpdateDto {
    public String name;
    public LocalDate dob;
}
