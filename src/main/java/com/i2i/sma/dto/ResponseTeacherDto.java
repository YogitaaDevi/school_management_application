package com.i2i.sma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTeacherDto {

    private int id;
    private String name;
    private String subject;
    private Set<ResponseGradeDto> grades;
    private ResponseCabinDto cabin;

}
