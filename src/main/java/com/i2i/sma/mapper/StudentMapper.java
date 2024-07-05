package com.i2i.sma.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.i2i.sma.dto.RequestStudentDto;
import com.i2i.sma.dto.ResponseStudentDto;
import com.i2i.sma.dto.ViewStudentDto;
import com.i2i.sma.models.Student;
import com.i2i.sma.utils.DateUtil;

@Component
public class StudentMapper {
    @Autowired
    private GradeMapper gradeMapper;

    public Student requestDtoToEntity(RequestStudentDto requestStudentDto){
        return Student.builder().name(requestStudentDto.getName())
                .dob(requestStudentDto.getDob())
                .build();
    }

    public ResponseStudentDto entityToResponseDto(Student student){
        return ResponseStudentDto.builder().id(student.getId())
                .name(student.getName()).dob(student.getDob())
                .age(DateUtil.calculateDifferenceBetweenDates(student.getDob()))
                .grade(gradeMapper.entityToResponseDto(student.getGrade())).build();
    }

    public ViewStudentDto entityToResponseViewDto(Student student){
        return ViewStudentDto.builder().id(student.getId())
                .name(student.getName()).dob(student.getDob())
                .age(DateUtil.calculateDifferenceBetweenDates(student.getDob()))
                .build();
    }
}
