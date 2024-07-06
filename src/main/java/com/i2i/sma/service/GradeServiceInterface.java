package com.i2i.sma.service;

import java.util.List;
import java.util.UUID;

import com.i2i.sma.dto.RequestGradeDto;
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

    /**
     * <p>
     * This method get the specified class standard and section from the database.
     * </p>
     * @param requestGradeDto {@link RequestGradeDto}
     * @return grade {@link Grade}
     * @throws SchoolManagementException this occurs when anything went wrong while checking the grade data.
     */
    Grade getGradeOrCreateNewGrade(RequestGradeDto requestGradeDto)
            throws SchoolManagementException;

    /**
     * <p>
     * This method retrieves all Grade data(each standard and section that are available in Grade).
     * </p>
     * @return list of all Grades {@link ResponseGradeDto}.
     * @throws SchoolManagementException this occurs when anything went wrong while fetching all details.
     */
    List<ResponseGradeDto> fetchGradeDetails() throws SchoolManagementException;
    /**
     * <p>
     * This method retrieves a particular Grade data based on id.
     * </p>
     * @return Grade details {@link ViewGradeDto}
     * @throws SchoolManagementException this occurs when anything went wrong while fetching a data.
     */
    ViewGradeDto fetchGradeById(UUID id) throws SchoolManagementException;

    /**
     * <p>
     * This method deletes a grade record along with the students in it
     * from the database based on the provided grade id.
     * </p>
     * @param id a unique identifier that represents each standard and section.
     * @return true if the specified grade is successfully deleted or else returns false.
     * @throws SchoolManagementException this occurs when anything went wrong while removing a data.
     */
    boolean isDeleteGrade(UUID id) throws SchoolManagementException;
}
