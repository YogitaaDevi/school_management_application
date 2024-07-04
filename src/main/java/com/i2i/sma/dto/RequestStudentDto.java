package com.i2i.sma.dto;

import java.time.LocalDate;

public class RequestStudentDto {

    private String name;
    private LocalDate dob;
    private RequestGradeDto grade;

    public RequestStudentDto() {
    }

    public RequestGradeDto getGrade() {
        return grade;
    }

    public void setGrade(RequestGradeDto grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "RequestStudentDto{" +
                "name='" + name + '\'' +
                ", dob=" + dob +
                ", grade=" + grade +
                '}';
    }

}
