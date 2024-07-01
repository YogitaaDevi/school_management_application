package com.i2i.sma.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sma.dao.StudentDao;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Student;


/**
 * <p>
 * This class is responsible for managing student and their associated standard, section records.
 * It provides functionalities to add new students to the database, fetch all student records,
 * search for students, and remove students from the student details.
 * </p>
 */
@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao ;
    @Autowired
    private GradeService gradeService ;

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
     * @param name
     *   the name of the student in String. Only alphabets are allowed.
     * @param dob
     *   the date of birth of the student in YYYY-MM-DD format
     * @param grade
     *   that contains: 1. id(unique identifier of a particular grade)
     *                  2. standard and section the student is enrolling in. Only 1-12 are acceptable.
     * @return the created Student details.
     * @throws SchoolManagementException this occurs when anything went wrong while inserting a data.
     */
    public Student addStudentToGrade(String name, LocalDate dob, Grade grade) throws SchoolManagementException {
        Student student = new Student();
        student.setName(name);
        student.setDob(dob);
        student.setGrade(grade);
        studentDao.insertStudent(student);
        return student;
    }

    /**
     * <p>
     * This method provides grade details of the specified standard and section to the database.
     * It takes the student's standard, and section as parameters.
     * The method performs the following steps:
     * 1. It checks if the specified standard and section is present.
     * 2. If present, it returns the grade details.
     * 3. If not present, it adds the particular standard and section to the database
     * and returns that grade details.
     * </p>
     *
     * @param standard
     *   the standard of the grade in numerical. Only 1-12 numbers are acceptable.
     * @param section
     *   the section of the grade in string. Only alphabets are allowed
     * @return gradeDetails.
     *   that contains: 1. id of the grade(a unique identifier represents each grade)
     *                  2. standard of the grade in integer.
     *                  3. the section of the grade in string.
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while inserting a data.
     */
    public Grade getStudentGrade(int standard, String section) throws SchoolManagementException {
        Grade gradeDetails = gradeService.getGrade(standard, section);
        if (null == gradeDetails) {
            Grade grade = new Grade();
            grade.setStandard(standard);
            grade.setSection(section);
            gradeDetails = gradeService.addGrade(grade);
        }
        return gradeDetails;
    }

    /**
     * <p>
     * This method retrieves all the students record from the database.
     * </p>
     *
     * @return all Student details in form of list to display it to the enduser.
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while retrieving data.
     */
    public List<Student> fetchStudents() throws SchoolManagementException {
        return studentDao.getDetails();
    }

    /**
     * <p>
     * This method retrieves a particular student record
     * from the database based on the id (unique identifier represents each student).
     * </p>
     *
     * @param id
     *   the unique identifier of the student to be retrieved.
     * @return
     *   Student corresponding to the provided ID if found. Else null if no such student is found.
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while searching a data.
     */
    public Student findStudent(int id) throws SchoolManagementException {
        return studentDao.findStudentById(id);
    }

    /**
     * <p>
     * This method deletes a particular student record
     * from the database based on the provided student ID.
     * If the given id matches, it removes the student with the specified ID.
     * </p>
     *
     * @param id
     *   the unique identifier of the student to be deleted.
     * @return
     *   true if the specified student id is deleted successfully or else returns false
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while removing a data.
     */
    public boolean isDeleteStudent(int id) throws SchoolManagementException {
        return studentDao.isRemoveStudent(id);
    }
}
        