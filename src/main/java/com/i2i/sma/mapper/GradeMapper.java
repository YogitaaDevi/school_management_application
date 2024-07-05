package com.i2i.sma.mapper;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.i2i.sma.dto.*;
import com.i2i.sma.models.Grade;

@Component
public class GradeMapper {

    public Grade requestDtoToEntity(RequestGradeDto requestGradeDto) {
        return Grade.builder().standard(requestGradeDto.getStandard())
                .section(requestGradeDto.getSection())
                .build();
    }

    public ResponseGradeDto entityToResponseDto(Grade grade) {
        return ResponseGradeDto.builder().id(grade.getId())
                .standard(grade.getStandard())
                .section(grade.getSection())
                .build();
    }

    public ViewGradeDto entityToResponseViewDto(Grade grade, Set<ViewStudentDto> student, Set<ViewTeacherDto> teacher) {
        return ViewGradeDto.builder().id(grade.getId())
                .standard(grade.getStandard())
                .section(grade.getSection())
                .students(student)
                .teachers(teacher).build();
    }
}
