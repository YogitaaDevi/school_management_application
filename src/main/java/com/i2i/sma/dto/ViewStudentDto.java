package com.i2i.sma.dto;

import com.i2i.sma.models.Student;
import com.i2i.sma.utils.DateUtil;

import java.time.LocalDate;

public class ViewStudentDto {
    private int id;
    private String name;
    private LocalDate dob;
    private int age;

    public ViewStudentDto(int id, String name, LocalDate dob, int age) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.age = age;
    }

    public ViewStudentDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.dob = student.getDob();
        this.age = DateUtil.calculateDifferenceBetweenDates(dob);
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
                '}';
    }
}

