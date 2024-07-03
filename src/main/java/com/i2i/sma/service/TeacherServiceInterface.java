package com.i2i.sma.service;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TeacherServiceInterface {
    Teacher addNewTeacher(String name, String subject, Set<Grade> grades) throws SchoolManagementException;

    Grade getTeacherGrade(int standard, String section) throws SchoolManagementException;

    List<Teacher> fetchTeachers() throws SchoolManagementException;

    Optional<Teacher> findTeacher(int id) throws SchoolManagementException;

    boolean isDeleteTeacher(int id) throws SchoolManagementException;
}
