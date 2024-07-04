package com.i2i.sma.service;

import java.util.List;

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
    ResponseTeacherDto addNewTeacher(RequestTeacherDto requestTeacherDto) throws SchoolManagementException;

    List<ViewTeacherDto> fetchTeachers() throws SchoolManagementException;

    ResponseTeacherDto findTeacher(int id) throws SchoolManagementException;

    boolean isDeleteTeacher(int id) throws SchoolManagementException;
}
