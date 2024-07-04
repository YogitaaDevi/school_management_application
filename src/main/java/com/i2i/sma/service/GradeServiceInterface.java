package com.i2i.sma.service;

import java.util.List;

import com.i2i.sma.dto.ResponseGradeDto;
import com.i2i.sma.dto.ViewGradeDto;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;

/**
 * <p>
 * This interface is responsible for managing methods that is needed for the grade service class.
 * GradeService implements this interface and provides functionalities :
 * 1. Add grade details with specified standard and section
 * 2. View grade details
 * 3. Search grade details
 * 4. Remove grade details
 * </p>
 */
public interface GradeServiceInterface {
    Grade getGradeOrCreateNewGrade(int standard, String section) throws SchoolManagementException;

    List<ResponseGradeDto> fetchGradeDetails() throws SchoolManagementException;

    ViewGradeDto fetchGradeById(int id) throws SchoolManagementException;

    boolean isDeleteGrade(int id) throws SchoolManagementException;
}
