package com.i2i.sma.dto;

import com.i2i.sma.models.Teacher;

import java.util.Set;

public class ViewTeacherDto {
    private int id;
    private String name;
    private String subject;


    public ViewTeacherDto(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.subject = teacher.getSubject();
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
        return "RequestTeacherDto{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
