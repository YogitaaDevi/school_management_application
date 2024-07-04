package com.i2i.sma.dto;

import java.util.Set;

public class RequestTeacherDto {
    private String name;
    private String subject;
    private Set<RequestGradeDto> grades;

    public RequestTeacherDto(String name, String subject, Set<RequestGradeDto> grades) {
        this.name = name;
        this.subject = subject;
        this.grades = grades;
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

    public Set<RequestGradeDto> getGrades() {
        return grades;
    }

    public void setGrades(Set<RequestGradeDto> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "RequestTeacherDto{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", grades=" + grades +
                '}';
    }
}
