package com.i2i.sma.dto;

import com.i2i.sma.models.Grade;

import java.util.Set;

public class ResponseGradeDto {
    private int id;
    private int standard;
    private String section;


    public ResponseGradeDto(Grade grade) {
        this.id = grade.getId();
        this.standard = grade.getStandard();
        this.section = grade.getSection();
    }
    public ResponseGradeDto(Set<ResponseGradeDto> grades) {
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

    @Override
    public String toString() {
        return "ResponseGradeDto{" +
                "id=" + id +
                ", standard=" + standard +
                ", section='" + section + '\'' +
                '}';
    }

}
