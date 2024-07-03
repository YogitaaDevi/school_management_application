package com.i2i.sma.service;

import com.i2i.sma.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentServiceInterface {

    List<Student> getAllStudents();

    Optional<Student> getStudentById(int id);
}
