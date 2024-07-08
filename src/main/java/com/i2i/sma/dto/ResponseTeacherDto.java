package com.i2i.sma.dto;

import java.util.Set;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * This class is responsible for managing the response teacher details that contains
 * 1.teacher name
 * 2.handling subject
 * 3.set of response grades {@link ResponseGradeDto}
 * 4.cabin details {@link ResponseCabinDto}
 * </p>
 */

@Builder
@Getter
@Setter
public class ResponseTeacherDto {

    private UUID id;
    private String name;
    private String subject;
    private Set<ResponseGradeDto> grades;
    private ResponseCabinDto cabin;

}
