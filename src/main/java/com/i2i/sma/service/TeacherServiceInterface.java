package com.i2i.sma.service;

import java.util.List;
import java.util.UUID;

import com.i2i.sma.dto.RequestTeacherDto;
import com.i2i.sma.dto.ResponseTeacherDto;
import com.i2i.sma.dto.ViewTeacherDto;
import com.i2i.sma.exception.SchoolManagementException;

/**
 * <p>
 * This interface is responsible for managing methods that is needed for the teacher service class.
 * TeacherService implements this interface and provides functionalities :
 * 1. Add teacher details in their cabin
 * 2. View teacher details
 * 3. Search teacher details
 * 4. Remove teacher details
 * </p>
 */
public interface TeacherServiceInterface {

    /**
     * <p>
     * This method deals with assigning the teacher in their associated standard and section.
     * It takes the standard, section and grade details .
     * This method provides the following functionalities:
     * 1.It creates a teacher with their name, subject and grade.
     * 2.Then it creates a cabin for a particular teacher along with the laptopId.
     *
     * @param requestTeacherDto {@link RequestTeacherDto}
     * @return teacher {@link ResponseTeacherDto}
     * @throws SchoolManagementException this occurs when anything went wrong while adding teacher details.
     */
    ResponseTeacherDto addNewTeacher(RequestTeacherDto requestTeacherDto)
            throws SchoolManagementException;

    /**
     * <p>
     * This method retrieves all the teacher record from the database.
     * </p>
     * @return all Teacher details {@link ViewTeacherDto}.
     * @throws SchoolManagementException this occurs when anything went wrong while retrieving data.
     */
    List<ViewTeacherDto> fetchTeachers() throws SchoolManagementException;

    /**
     * <p>
     * This method retrieves a particular teacher record from the database based on the id (unique identifier represents each student).
     * </p>
     * @param id the unique identifier of the teacher to be retrieved
     * @return teacher {@link ResponseTeacherDto} if id found. Else null
     * @throws SchoolManagementException this occurs when anything went wrong while searching a data.
     */
    ResponseTeacherDto findTeacher(UUID id) throws SchoolManagementException;

    /**
     * <p>
     * This method updates a particular student record in the database
     * </p>
     * @param viewTeacherDto {@link ViewTeacherDto}
     * @return updated teacher data {@link ResponseTeacherDto}.
     * @throws SchoolManagementException this occurs when anything went wrong while searching a data.
     */
    ResponseTeacherDto upgradeTeacher(ViewTeacherDto viewTeacherDto) throws SchoolManagementException;

    /**
     * <p>
     * This method deletes a teacher record from the database based on the provided teacher ID.
     * If the given id matches, it removes the teacher with the specified ID.
     * </p>
     * @param id the unique identifier of the teacher to be deleted
     * @return true if the teacher is deleted successfully or else returns false.
     * @throws SchoolManagementException this occurs when anything went wrong while removing a data.
     */
    boolean isDeleteTeacher(UUID id) throws SchoolManagementException;
}
