package com.i2i.sma.mapper;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.i2i.sma.dto.RequestTeacherDto;
import com.i2i.sma.dto.ResponseGradeDto;
import com.i2i.sma.dto.ResponseTeacherDto;
import com.i2i.sma.dto.ViewTeacherDto;
import com.i2i.sma.models.Teacher;

@Component
public class TeacherMapper {
    @Autowired
    private CabinMapper cabinMapper;
    @Autowired
    private GradeMapper gradeMapper;

    public Teacher requestDtoToEntity(RequestTeacherDto requestTeacherDto){
        return Teacher.builder()
                .name(requestTeacherDto.getName()).subject(requestTeacherDto.getSubject())
                .build();
    }

    public ViewTeacherDto entityToResponseViewDto(Teacher teacher){
        return ViewTeacherDto.builder().id(teacher.getId())
                .name(teacher.getName()).subject(teacher.getSubject())
                .build();
    }

    public ResponseTeacherDto entityToResponseDto(Teacher teacher, Set<ResponseGradeDto> responseGrade){
        return ResponseTeacherDto.builder().id(teacher.getId())
                .name(teacher.getName()).subject(teacher.getSubject())
                .cabin(cabinMapper.entityToResponseDto(teacher.getCabin()))
                .grades(responseGrade).build();
    }
}
