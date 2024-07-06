package com.i2i.sma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * This class is responsible for managing the view grade details that contains
 * 1.grade id
 * 2.specific standard and section
 * 3.set of students {@link ViewStudentDto}
 * 4.set of teachers {@link ViewTeacherDto}
 */
public class ViewGradeDto {

    private UUID id;
    private int standard;
    private String section;
    private Set<ViewStudentDto> students;
    private Set<ViewTeacherDto> teachers;
}
