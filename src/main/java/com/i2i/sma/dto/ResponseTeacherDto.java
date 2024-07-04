package com.i2i.sma.dto;

import com.i2i.sma.models.Teacher;
import org.apache.catalina.util.ResourceSet;

import java.util.Set;

public class ResponseTeacherDto {
    private int id;
    private String name;
    private String subject;
    private Set<ResponseGradeDto> grades;
    private ResponseCabinDto cabin;

    public ResponseTeacherDto(Teacher teacher, Set<ResponseGradeDto> createGrades, ResponseCabinDto cabinDetails) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.subject = teacher.getSubject();
        this.grades = createGrades;
        this.cabin = cabinDetails;
    }

    public ResponseTeacherDto(Teacher teacher, Set<ResponseGradeDto> grades) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.subject = teacher.getSubject();
        this.grades = grades;
        this.cabin = new ResponseCabinDto(teacher.getCabin());
    }

    public Set<ResponseGradeDto> getGrades() {
        return grades;
    }

    public void setGrades(Set<ResponseGradeDto> grades) {
        this.grades = grades;
    }

    public ResponseCabinDto getCabin() {
        return cabin;
    }

    public void setCabin(ResponseCabinDto cabin) {
        this.cabin = cabin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    @Override
    public String toString() {
        return "ResponseTeacherDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", grades=" + grades +
                ", cabin=" + cabin +
                '}';
    }
}
