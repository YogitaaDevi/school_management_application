package com.i2i.sma.service;

import com.i2i.sma.dto.RequestStudentDto;
import com.i2i.sma.dto.ResponseStudentDto;
import com.i2i.sma.dto.ViewStudentDto;
import com.i2i.sma.exception.SchoolManagementException;

import java.util.List;

/**
 * <p>
 * This interface is responsible for managing methods that is needed for the student service class.
 * StudentService implements this interface and provides functionalities :
 * 1. Add new students to the database
 * 2. Fetch all student records,
 * 3. Search for students and
 * 4. Remove students from the student details.
 * </p>
 */
public interface StudentServiceInterface {

    /**
     * <p>
     * This method creates and adds a new student to the specified grade to the database.
     * It takes the student's name, date of birth (DOB), class standard, and section as parameters.
     * The method performs the following steps:
     * 1. Creates a new Student object and sets its name, DOB and grade details.
     * 2. And pass the student record to the student database.
     * 3. Finally, it returns the created Student object.
     * </p>
     *
     * @param requestStudentDto {@link RequestStudentDto}
     * @return the created Student details. {@link ResponseStudentDto}
     * @throws SchoolManagementException occurs when anything went wrong while inserting a data.
     */
    ResponseStudentDto addStudentToGrade(RequestStudentDto requestStudentDto)
            throws SchoolManagementException;

    /**
     * <p>
     * This method retrieves all the students record from the database.
     * </p>
     * @return all Student details in form of list to display it to the end user.
     * @throws SchoolManagementException this occurs when anything went wrong while retrieving data.
     */
    List<ViewStudentDto> fetchStudents() throws SchoolManagementException;

    /**
     * <p>
     * This method retrieves a particular student record
     * from the database based on the id (unique identifier represents each student).
     * </p>
     * @param id
     * the unique identifier of the student to be retrieved.
     * @return Student {@link ResponseStudentDto}  if id found. Else null.
     * @throws SchoolManagementException this occurs when anything went wrong while searching a data.
     */
    ResponseStudentDto findStudent(int id) throws SchoolManagementException;

    /**
     * <p>
     * This method updates a particular student record in the database
     * </p>
     * @param viewStudentDto {@link ViewStudentDto}
     * @return updated Student data corresponding to the provided ID if found.
     * @throws SchoolManagementException this occurs when anything went wrong while searching a data.
     */
    ResponseStudentDto upgradeStudent(ViewStudentDto viewStudentDto)
            throws SchoolManagementException;

    /**
     * <p>
     * This method deletes a particular student record
     * from the database based on the provided student ID.
     * If the given id matches, it removes the student with the specified ID.
     * </p>
     * @param id the unique identifier of the student to be deleted.
     * @return true if the specified student id is deleted successfully .Else returns false
     * @throws SchoolManagementException this occurs when anything went wrong while removing a data.
     */
    boolean isDeleteStudent(int id) throws SchoolManagementException;
}
