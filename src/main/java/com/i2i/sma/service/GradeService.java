package com.i2i.sma.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sma.dto.*;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.mapper.GradeMapper;
import com.i2i.sma.mapper.StudentMapper;
import com.i2i.sma.mapper.TeacherMapper;
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
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * {@inheritDoc GradeServiceInterface}
     */
    public Grade getGradeOrCreateNewGrade(RequestGradeDto requestGradeDto)
            throws SchoolManagementException {
        try {
            Grade grade = gradeMapper.requestDtoToEntity(requestGradeDto);
            Grade gradeDetails = gradeRepository.findByStandardAndSection(grade.getStandard(),
                    grade.getSection());
            if (null == gradeDetails) {
                logger.debug("PROCESS STARTED: INSERTING GRADE DETAILS OF STANDARD: {} ," +
                        "SECTION: {}", grade.getStandard(), grade.getSection());
                return gradeRepository.save(grade);
            }
            return gradeDetails;
        } catch (Exception e) {
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE CHECKING THE" +
                    " GRADE DETAILS OF STANDARD " + requestGradeDto.getStandard()
                    + " AND SECTION " + requestGradeDto.getSection(), e);
        }
    }

    /**
     * {@inheritDoc GradeServiceInterface }
     */
    public List<ResponseGradeDto> fetchGradeDetails() throws SchoolManagementException {
        try {
            List<Grade> grades = gradeRepository.findAll();
            if (!grades.isEmpty()) {
                logger.debug("PROCESS STARTED: FETCHING ALL GRADE DETAILS");
                List<ResponseGradeDto> allGrades = new ArrayList<>();
                for (Grade grade : grades) {
                    allGrades.add(gradeMapper.entityToResponseDto(grade));
                }
                return allGrades;
            }
        } catch (Exception e) {
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE FETCHING ALL GRADE DETAILS", e);
        }
        return null;
    }

    /**
     * {@inheritDoc GradeServiceInterface }
     */
    public ViewGradeDto fetchGradeById(UUID id) throws SchoolManagementException {
        try {
            Optional<Grade> grade = gradeRepository.findById(id);
            if (grade.isPresent()) {
                logger.debug("PROCESS STARTED: FETCHING A GRADE DETAILS OF ID {}", id);
                Set<ViewStudentDto> students = new HashSet<>();
                Set<ViewTeacherDto> teachers = new HashSet<>();
                for (Student student : grade.get().getStudents()) {
                    students.add(studentMapper.entityToResponseViewDto(student));
                }
                for (Teacher teacher : grade.get().getTeachers()) {
                    teachers.add(teacherMapper.entityToResponseViewDto(teacher));
                }
                return (gradeMapper.entityToResponseViewDto(grade.get(), students, teachers));
            }
        } catch (Exception e) {
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE RETRIEVING THE GRADE DETAILS OF ID " + id, e);
        }
        return null;
    }

    /**
     * {@inheritDoc GradeServiceInterface }
     */
    public boolean isDeleteGrade(UUID id) throws SchoolManagementException {
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