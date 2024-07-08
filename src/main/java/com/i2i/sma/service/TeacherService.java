package com.i2i.sma.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sma.dto.RequestGradeDto;
import com.i2i.sma.dto.RequestTeacherDto;
import com.i2i.sma.dto.RequestTeacherUpdateDto;
import com.i2i.sma.dto.ResponseGradeDto;
import com.i2i.sma.dto.ResponseTeacherDto;
import com.i2i.sma.dto.ViewTeacherDto;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.mapper.GradeMapper;
import com.i2i.sma.mapper.TeacherMapper;
import com.i2i.sma.models.Cabin;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Teacher;
import com.i2i.sma.repository.TeacherRepository;

/**
 * <p>
 * This class is responsible for managing teacher details with their associated standard and section.
 * It provides functionalities :
 * 1.Add teacher details in their cabin
 * 2.View teacher details
 * 3.Search teacher details
 * 4.Remove teacher details
 * </p>
 */
@Service
public class TeacherService implements TeacherServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);
    @Autowired
    private GradeService gradeService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private GradeMapper gradeMapper;

    /**
     * {@inheritDoc TeacherServiceInterface}
     */
    public ResponseTeacherDto addNewTeacher(RequestTeacherDto requestTeacherDto)
            throws SchoolManagementException {
        Teacher teacher = teacherMapper.requestDtoToEntity(requestTeacherDto);
        Set<RequestGradeDto> requestGrades= requestTeacherDto.getGrades();
        Set<Grade> grades = new HashSet<>();
        Set<ResponseGradeDto> responseGrade = new HashSet<>();
        for (RequestGradeDto requestGrade : requestGrades) {
            Grade gradeDetail = gradeService.getGradeOrCreateNewGrade(requestGrade);
            grades.add(gradeDetail);
            responseGrade.add(gradeMapper.entityToResponseDto(gradeDetail));
        }
        teacher.setGrades(grades);
        Cabin cabin = new Cabin();
        cabin.setLaptopId(generateRandom());
        cabin.setTeacher(teacher);
        teacher.setCabin(cabin);
        try {
            logger.debug("PROCESS STARTED: INSERTING TEACHER DETAILS OF NAME: {} ," +
                    "SUBJECT: {}, GRADES: {}", teacher.getName(), teacher.getSubject(), teacher.getGrades());
            return teacherMapper.entityToResponseDto(teacherRepository.save(teacher), responseGrade);
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE INSERTING STUDENT" +
                    " DETAILS OF NAME " + teacher.getName());
        }
    }

    /**
     * <p>
     * This method deals with assigning a random value for the laptopId.
     * @return a random number.
     */
    public int generateRandom() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    /**
     * {@inheritDoc TeacherServiceInterface}
     */
    public List<ViewTeacherDto> fetchTeachers() throws SchoolManagementException {
        try {
            List<Teacher> teachers = teacherRepository.findAll();
            if (!teachers.isEmpty()) {
                logger.debug("PROCESS STARTED: FETCHING ALL TEACHERS DETAILS");
                List<ViewTeacherDto> allTeachers = new ArrayList<>();
                for (Teacher teacher : teachers) {
                    allTeachers.add(teacherMapper.entityToResponseViewDto(teacher));
                }
                return allTeachers;
            }
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING ALL TEACHER DETAILS");
        }
        return null;
    }

    /**
     * {@inheritDoc TeacherServiceInterface}
     */
    public ResponseTeacherDto findTeacher(UUID id) throws SchoolManagementException {
        try {
            Optional<Teacher> searchedTeacher = teacherRepository.findById(id);
            if (searchedTeacher.isPresent()) {
                logger.debug("PROCESS STARTED: FETCHING A TEACHER DETAILS OF ID {}" , id);
                Set<ResponseGradeDto> grades = new HashSet<>();
                for (Grade grade : searchedTeacher.get().getGrades()) {
                    grades.add(gradeMapper.entityToResponseDto(grade));
                }
                return teacherMapper.entityToResponseDto(searchedTeacher.get(), grades);
            }
        } catch (Exception e) {
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE RETRIEVING THE TEACHER DETAILS OF ID " + id, e);
        }
        return null;
    }

    /**
     * {@inheritDoc TeacherServiceInterface}
     */
    public ResponseTeacherDto upgradeTeacher(UUID id, RequestTeacherUpdateDto requestTeacherUpdateDto) throws SchoolManagementException {
        try {
            Optional<Teacher> teacher = teacherRepository.findById(id);
            if(teacher.isPresent()){
                Teacher teacherDetails = teacher.get();
                teacherDetails.setName(requestTeacherUpdateDto.getName());
                teacherDetails.setSubject(requestTeacherUpdateDto.getSubject());
                Set<ResponseGradeDto> grades = new HashSet<>();
                for (Grade grade : teacherDetails.getGrades()) {
                    grades.add(gradeMapper.entityToResponseDto(grade));
                }
                logger.debug("PROCESS STARTED: UPDATING A TEACHER DETAILS OF ID {}", id);
                return teacherMapper.entityToResponseDto(teacherRepository.save(teacherDetails), grades);
            }
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE UPDATING " +
                    "THE TEACHER DETAIL OF ID " + id);
        }
        return null;
    }

    /**
     * {@inheritDoc TeacherServiceInterface}
     */
    public boolean isDeleteTeacher(UUID id) throws SchoolManagementException {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            logger.debug("PROCESS STARTED: DELETING A TEACHER DETAILS OF ID {}" , id);
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}