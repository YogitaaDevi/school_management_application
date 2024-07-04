package com.i2i.sma.dto;

import com.i2i.sma.models.Student;
import com.i2i.sma.utils.DateUtil;

import java.time.LocalDate;

public class ResponseStudentDto {

    private int id;
    private String name;
    private LocalDate dob;
    private int age;
    private ResponseGradeDto grade;

    public ResponseStudentDto(int id, String name, LocalDate dob, int age, ResponseGradeDto grade) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.grade = grade;
    }

    public ResponseStudentDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.dob = student.getDob();
        this.age = DateUtil.calculateDifferenceBetweenDates(dob);
        this.grade = new ResponseGradeDto(student.getGrade());
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

    public ResponseGradeDto getGrade() {
        return grade;
    }

    public void setGrade(ResponseGradeDto grade) {
        this.grade = grade;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ResponseStudentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }
}
