package com.i2i.sma.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sma.dto.RequestStudentDto;
import com.i2i.sma.dto.RequestStudentUpdateDto;
import com.i2i.sma.dto.ResponseStudentDto;
import com.i2i.sma.dto.ViewStudentDto;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.mapper.GradeMapper;
import com.i2i.sma.mapper.StudentMapper;
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
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private GradeMapper gradeMapper;

    /**
     * {@inheritDoc StudentServiceInterface}
     */
    public ResponseStudentDto addStudentToGrade(RequestStudentDto requestStudentDto)
            throws SchoolManagementException {
        Student student = studentMapper.requestDtoToEntity(requestStudentDto);
        Grade gradeDetail = gradeService.getGradeOrCreateNewGrade(requestStudentDto.getGrade());
        student.setGrade(gradeDetail);
        try {
            logger.debug("PROCESS STARTED: INSERTING STUDENT DETAILS OF NAME: {} ," +
                    "DOB: {}, GRADE: {}", student.getName(), student.getDob(), student.getGrade());
            return studentMapper.entityToResponseDto(studentRepository.save(student));
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE INSERTING STUDENT" +
                    " DETAILS OF NAME " + student.getName());
        }
    }

    /**
     * {@inheritDoc StudentServiceInterface}
     */
    public List<ViewStudentDto> fetchStudents() throws SchoolManagementException {
        try {
            List<Student> students = studentRepository.findAll();
            if (!students.isEmpty()) {
                logger.debug("PROCESS STARTED: FETCHING ALL STUDENTS DETAILS");
                List<ViewStudentDto> allStudents = new ArrayList<>();
                for (Student student : students) {
                    allStudents.add(studentMapper.entityToResponseViewDto(student));
                }
                return allStudents;
            }
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING" +
                    " ALL STUDENT DETAILS");
        }
        return null;
    }

    /**
     * {@inheritDoc StudentServiceInterface}
     */
    public ResponseStudentDto findStudent(UUID id) throws SchoolManagementException {
        try {
            Optional<Student> student = studentRepository.findById(id);
            if (student.isPresent()) {
                logger.debug("PROCESS STARTED: FETCHING A STUDENT DETAILS OF ID {}", id);
                return studentMapper.entityToResponseDto(student.get());
            }
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE SEARCHING " +
                    "THE STUDENT DETAIL OF ID" + id);
        }
        return null;
    }

    /**
     * {@inheritDoc StudentServiceInterface}
     */
    public ResponseStudentDto upgradeStudent(UUID id, RequestStudentUpdateDto requestStudentUpdateDto)
            throws SchoolManagementException {
        try {
            Optional<Student> student = studentRepository.findById(id);
            if(student.isPresent()){
                Student studentDetails = student.get();
                studentDetails.setName(requestStudentUpdateDto.getName());
                studentDetails.setDob(requestStudentUpdateDto.getDob());
                logger.debug("PROCESS STARTED: UPDATING A STUDENT DETAILS OF ID {}",
                        id);
                return studentMapper.entityToResponseDto(studentRepository.save(studentDetails));
            }
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE UPDATING " +
                    "THE STUDENT DETAIL OF ID" + id);
        }
        return null;
    }

    /**
     * {@inheritDoc StudentServiceInterface}
     */
    public boolean isDeleteStudent(UUID id) throws SchoolManagementException {
        try {
            Optional<Student> student = studentRepository.findById(id);
            if (student.isPresent()) {
                logger.debug("PROCESS STARTED: DELETING A STUDENT DETAILS OF ID {}", id);
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
