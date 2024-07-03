package com.i2i.sma.service;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeServiceInterface {
    Grade getGradeOrCreateNewGrade(int standard, String section) throws SchoolManagementException;
    List<Grade> fetchGradeDetails() throws SchoolManagementException;
    Optional<Grade> fetchGradeById(int id) throws SchoolManagementException;
    boolean isDeleteGrade(int id) throws SchoolManagementException;
}
