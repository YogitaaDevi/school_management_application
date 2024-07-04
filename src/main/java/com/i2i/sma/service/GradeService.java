package com.i2i.sma.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sma.dto.*;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Student;
import com.i2i.sma.models.Teacher;
import com.i2i.sma.repository.GradeRepository;

/**
 * <p>
 * This class is responsible for managing grade details.
 * It provides functionalities :
 * 1.Add grade details with specified standard and section
 * 2.View grade details
 * 3.Search grade details
 * 4.Remove grade details
 * </p>
 */
@Service
public class GradeService implements GradeServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(GradeService.class);
    @Autowired
    private GradeRepository gradeRepository;

    /**
     * <p>
     * This method get the specified class standard and section from the database.
     * </p>
     *
     * @param standard the class standard of the grade in numerical. Only 1-12 numbers are acceptable.
     * @param section  the section of the grade in string. Only alphabets are allowed.
     * @return grade
     * contains: 1. id of the grade (a unique identifier)
     * 2. the specified class standard and section.
     * @throws SchoolManagementException this occurs when anything went wrong while checking the grade data.
     */
    public Grade getGradeOrCreateNewGrade(int standard, String section) throws SchoolManagementException {
        try {
            Grade gradeDetails = gradeRepository.findByStandardAndSection(standard, section);
            if (null == gradeDetails) {
                logger.debug("PROCESS STARTED: INSERTING GRADE DETAILS OF STANDARD: {} ," +
                        "SECTION: {}", standard, section);
                return gradeRepository.save(new Grade(standard, section));
            }
            return gradeDetails;
        } catch (Exception e) {
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE CHECKING THE" +
                    " GRADE DETAILS OF STANDARD " + standard + " AND SECTION " + section, e);
        }
    }

    /**
     * <p>
     * This method retrieves all Grade data(each standard and section that are available in Grade).
     * </p>
     *
     * @return list of all data from Grade.
     * contains standard, section and their corresponding grade ID(a unique identifier).
     * @throws SchoolManagementException this occurs when anything went wrong while fetching all details.
     */
    public List<ResponseGradeDto> fetchGradeDetails() throws SchoolManagementException {
        try {
            List<Grade> grades = gradeRepository.findAll();
            if (!grades.isEmpty()) {
                logger.debug("PROCESS STARTED: FETCHING ALL GRADE DETAILS");
                List<ResponseGradeDto> allGrades = new ArrayList<>();
                for (Grade grade : grades) {
                    allGrades.add(new ResponseGradeDto(grade));
                }
                return allGrades;
            }
        } catch (Exception e) {
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE FETCHING ALL GRADE DETAILS", e);
        }
        return null;
    }

    /**
     * <p>
     * This method retrieves a particular Grade data.
     * </p>
     *
     * @return Grade
     * contains specific standard and section along with the students present in it.
     * @throws SchoolManagementException this occurs when anything went wrong while fetching a data.
     */
    public ViewGradeDto fetchGradeById(int id) throws SchoolManagementException {
        try {
            Optional<Grade> grade = gradeRepository.findById(id);
            if (grade.isPresent()) {
                logger.debug("PROCESS STARTED: FETCHING A GRADE DETAILS OF ID {}", id);
                Set<ViewStudentDto> students = new HashSet<>();
                Set<ViewTeacherDto> teachers = new HashSet<>();
                for (Student student : grade.get().getStudents()) {
                    students.add(new ViewStudentDto(student));
                }
                for (Teacher teacher : grade.get().getTeachers()) {
                    teachers.add(new ViewTeacherDto(teacher));
                }
                return (new ViewGradeDto(grade.get(), students, teachers));
            }
        } catch (Exception e) {
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE RETRIEVING THE GRADE DETAILS OF ID " + id, e);
        }
        return null;
    }

    /**
     * <p>
     * This method deletes a grade record along with the students in it
     * from the database based on the provided grade id.
     * </p>
     *
     * @param id a unique identifier that represents each standard and section.
     * @return true if the specified grade is successfully deleted or else returns false.
     * @throws SchoolManagementException this occurs when anything went wrong while removing a data.
     */
    public boolean isDeleteGrade(int id) throws SchoolManagementException {
        try {
            Optional<Grade> grade = gradeRepository.findById(id);
            if (grade.isPresent()) {
                logger.debug("PROCESS STARTED: DELETING A STUDENT DETAILS OF ID {}", id);
                gradeRepository.delete(grade.get());
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE DELETING THE GRADE DETAILS OF ID  "
                    + id, e);
        }
    }
}