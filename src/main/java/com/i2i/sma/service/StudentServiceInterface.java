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
    ResponseStudentDto addStudentToGrade(RequestStudentDto requestStudentDto) throws SchoolManagementException;

    List<ViewStudentDto> fetchStudents() throws SchoolManagementException;

    ResponseStudentDto findStudent(int id) throws SchoolManagementException;

    boolean isDeleteStudent(int id) throws SchoolManagementException;
}
