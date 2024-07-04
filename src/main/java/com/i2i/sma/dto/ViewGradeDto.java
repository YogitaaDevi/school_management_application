package com.i2i.sma.dto;

import com.i2i.sma.models.Grade;

import java.util.Set;

public class ViewGradeDto {
    private int id;
    private int standard;
    private String section;
    private Set<ViewStudentDto> students;
    private Set<ViewTeacherDto> teachers;

    public ViewGradeDto(Grade grade, Set<ViewStudentDto> students, Set<ViewTeacherDto> teachers) {
        this.id = grade.getId();
        this.standard = grade.getStandard();
        this.section = grade.getSection();
        this.students = students;
        this.teachers = teachers;
    }

    public ViewGradeDto() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Set<ViewStudentDto> getStudents() {
        return students;
    }

    public void setStudents(Set<ViewStudentDto> students) {
        this.students = students;
    }

    public Set<ViewTeacherDto> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<ViewTeacherDto> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "ViewGradeDto{" +
                "id=" + id +
                ", standard=" + standard +
                ", section='" + section + '\'' +
                ", students=" + students +
                ", teachers=" + teachers +
                '}';
    }
}
