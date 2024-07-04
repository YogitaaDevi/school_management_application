package com.i2i.sma.dto;

import com.i2i.sma.models.Grade;

import java.util.Set;

public class RequestGradeDto {
    private int standard;
    private String section;

    public RequestGradeDto(Grade grade) {
        this.standard = grade.getStandard();
        this.section = grade.getSection();
    }

    public RequestGradeDto(Set<Grade> grades) {
    }

    public int getStandard() {
        return standard;
    }

    public RequestGradeDto(int standard, String section) {
        this.standard = standard;
        this.section = section;
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
}

