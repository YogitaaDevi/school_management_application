package com.i2i.sma.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * This class is responsible for managing the requested teacher details that contains
 * 1.teacher name
 * 2.handling subject and
 * 3.set of requested grades {@link RequestGradeDto}
 * </p>
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTeacherDto {

    private String name;
    private String subject;
    private Set<RequestGradeDto> grades;
}
