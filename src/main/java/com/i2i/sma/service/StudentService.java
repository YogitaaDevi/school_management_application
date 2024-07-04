package com.i2i.sma.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sma.dto.RequestStudentDto;
import com.i2i.sma.dto.ResponseStudentDto;
import com.i2i.sma.dto.ViewStudentDto;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Student;
import com.i2i.sma.repository.StudentRepository;

/**
 * <p>
 * This class is responsible for managing student and their associated standard, section records.
 * It provides functionalities:
 * 1. Add new students to the database
 * 2. Fetch all student records,
 * 3. Search for students and
 * 4. Remove students from the student details.
 * </p>
 */
@Service
public class StudentService implements StudentServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentRepository studentRepository;

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
     * @param requestStudentDto that contains student details of : 1. name
     *                          2. date of birth
     *                          3. grade details of standard and section.
     * @return the created Student details.
     * @throws SchoolManagementException this occurs when anything went wrong while inserting a data.
     */
    public ResponseStudentDto addStudentToGrade(RequestStudentDto requestStudentDto) throws SchoolManagementException {
        Student student = new Student();
        student.setName(requestStudentDto.getName());
        student.setDob(requestStudentDto.getDob());
        Grade gradeDetail = gradeService.getGradeOrCreateNewGrade(requestStudentDto.getGrade().getStandard(), requestStudentDto.getGrade().getSection());
        student.setGrade(gradeDetail);
        try {
            logger.debug("PROCESS STARTED: INSERTING STUDENT DETAILS OF NAME: {} ," +
                    "DOB: {}, GRADE: {}", student.getName(), student.getDob(), student.getGrade());
            Student studentDetails = studentRepository.save(student);
            return (new ResponseStudentDto(studentDetails));
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE INSERTING STUDENT" +
                    " DETAILS OF NAME " + student.getName());
        }
    }

    /**
     * <p>
     * This method retrieves all the students record from the database.
     * </p>
     *
     * @return all Student details in form of list to display it to the enduser.
     * @throws SchoolManagementException this occurs when anything went wrong while retrieving data.
     */
    public List<ViewStudentDto> fetchStudents() throws SchoolManagementException {
        try {
            List<Student> students = studentRepository.findAll();
            if (!students.isEmpty()) {
                logger.debug("PROCESS STARTED: FETCHING ALL STUDENTS DETAILS");
                List<ViewStudentDto> allStudents = new ArrayList<>();
                for (Student student : students) {
                    allStudents.add(new ViewStudentDto(student));
                }
                return allStudents;
            }
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING ALL STUDENT DETAILS");
        }
        return null;
    }

    /**
     * <p>
     * This method retrieves a particular student record
     * from the database based on the id (unique identifier represents each student).
     * </p>
     *
     * @param id the unique identifier of the student to be retrieved.
     * @return Student corresponding to the provided ID if found. Else null if no such student is found.
     * @throws SchoolManagementException this occurs when anything went wrong while searching a data.
     */
    public ResponseStudentDto findStudent(int id) throws SchoolManagementException {
        try {
            Optional<Student> student = studentRepository.findById(id);
            if (student.isPresent()) {
                logger.debug("PROCESS STARTED: FETCHING A STUDENT DETAILS OF ID {}" , id);
                return new ResponseStudentDto(student.get());
            }
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE SEARCHING " +
                    "THE STUDENT DETAIL OF ID" + id);
        }
        return null;
    }

    /**
     * <p>
     * This method deletes a particular student record
     * from the database based on the provided student ID.
     * If the given id matches, it removes the student with the specified ID.
     * </p>
     *
     * @param id the unique identifier of the student to be deleted.
     * @return true if the specified student id is deleted successfully or else returns false
     * @throws SchoolManagementException this occurs when anything went wrong while removing a data.
     */
    public boolean isDeleteStudent(int id) throws SchoolManagementException {
        try {
            Optional<Student> student = studentRepository.findById(id);
            if (student.isPresent()) {
                logger.debug("PROCESS STARTED: DELETING A STUDENT DETAILS OF ID {}" , id);
                Student studentToDelete = student.get();
                studentToDelete.getGrade().getStudents().remove(studentToDelete);
                studentRepository.delete(studentToDelete);
                return true;
            }
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE DELETING" +
                    " THE STUDENT DETAILS OF ID" + id);
        }
        return false;
    }
}
