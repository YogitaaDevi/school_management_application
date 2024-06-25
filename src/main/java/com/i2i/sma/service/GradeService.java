package com.i2i.sma.service;

import java.util.List;

import com.i2i.sma.dao.GradeDao;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;


/**
 * <p>
 * This class is responsible for managing grade details.
 * It provides functionalities :
 * 1.Add grade details with specified standard and section
 * 2.View grade details
 * 3.Search grade details
 * 4.Removw grade details
 * </p>
 */
public class GradeService {
    private GradeDao gradeDao = new GradeDao();

    /**
     * <p>
     * This method get the specified class standard and section from the database.
     * </p>
     *
     * @param standard
     *   the class standard of the grade in numerical. Only 1-12 numbers are acceptable.
     * @param section
     *   the section of the grade in string. Only alphabets are allowed.
     * @return grade
     *   contains: 1. id of the grade (a unique identifier)
     *             2. the specified class standard and section.
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while checking the grade data.
     */
    public Grade getGrade(int standard, String section) throws SchoolManagementException {
        return gradeDao.fetchGrade(standard, section);
    }

    /**
     * <p>
     * This method deals with adding grade details in the database.
     * </p>
     *
     * @param grade
     *   contains: 1. the class standard of the grade in numerical. Only 1-12 numbers are acceptable.
     *             2. the section of the grade in string. Only alphabets are allowed.
     * @return grade
     *   contains: 1. id of the grade (a unique identifier)
     *             2. the specified class standard and section.
     * @throws SchoolManagementException this occurs when anything went wrong while adding the grade data.
     */
    public Grade addGrade(Grade grade) throws SchoolManagementException {
        return gradeDao.insertGrade(grade);
    }

    /**
     * <p>
     * This method retrieves all Grade data(each standard and section that are available in Grade).
     * </p>
     *
     * @return list of all data from Grade.
     *   contains standard, section and their corresponding grade ID(a unique identifier).
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while fetching all details.
     */
    public List<Grade> fetchGradeDetails() throws SchoolManagementException {
        return gradeDao.getDetails();
    }

    /**
     * <p>
     * This method retrieves a particular Grade data.
     * </p>
     *
     * @return Grade
     *   contains specific standard and section along with the students present in it.
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while fetching a data.
     */
    public Grade fetchGradeById(int id) throws SchoolManagementException {
        return gradeDao.findGradeById(id);
    }

    /**
     * <p>
     * This method deletes a grade record along with the students in it from the database based on the provided grade id.
     * </p>
     *
     * @param id
     *   a unique identifier that represents each standard and section.
     * @return
     *   true if the specified grade is successfully deleted or else returns false.
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while removing a data.
     */
    public boolean isDeleteGrade(int id) throws SchoolManagementException {
        return gradeDao.isRemoveGrade(id);
    }
}