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
@AllArgsConstructor
@NoArgsConstructor

/**
 * This class is responsible for managing the requested teacher details that contains
 * 1.teacher name
 * 2.handling subject and
 * 3.set of requested grades {@link RequestGradeDto}
 */
public class RequestTeacherDto {

    private String name;
    private String subject;
    private Set<RequestGradeDto> grades;
}
