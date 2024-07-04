package com.i2i.sma.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sma.dto.*;
import com.i2i.sma.exception.SchoolManagementException;
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

    /**
     * <p>
     * This method deals with assigning the teacher in their associated standard and section.
     * It takes the standard, section and grade details .
     * This method provides the following functionalities:
     * 1.It creates a teacher with their name, subject and grade.
     * 2.Then it creates a cabin for a particular teacher along with the laptopId.
     *
     * @param requestTeacherDto that contains teacher details of :
     *                          1. name
     *                          2. subject that is taken by the teacher
     *                          3. set of grades(standard & section) taken by the teacher
     * @return teacher
     * contains teacher id, name, handling subject, handling grade details.
     * @throws SchoolManagementException this occurs when anything went wrong while adding teacher details.
     */
    public ResponseTeacherDto addNewTeacher(RequestTeacherDto requestTeacherDto) throws SchoolManagementException {
        Teacher teacher = new Teacher();
        teacher.setName(requestTeacherDto.getName());
        teacher.setSubject(requestTeacherDto.getSubject());
        Set<RequestGradeDto> createGrades = requestTeacherDto.getGrades();
        Set<Grade> grades = new HashSet<>();
        Set<ResponseGradeDto> responseGrade = new HashSet<>();
        for (RequestGradeDto createGrade : createGrades) {
            Grade gradeDetail = gradeService.getGradeOrCreateNewGrade(createGrade.getStandard(), createGrade.getSection());
            grades.add(gradeDetail);
            responseGrade.add(new ResponseGradeDto(gradeDetail));
        }
        teacher.setGrades(grades);
        Cabin cabin = new Cabin();
        cabin.setLaptopId(generateRandom());
        cabin.setTeacher(teacher);
        teacher.setCabin(cabin);
        try {
            Teacher teacherDetails = teacherRepository.save(teacher);
            return (new ResponseTeacherDto(teacherDetails, responseGrade,
                    new ResponseCabinDto(teacherDetails.getCabin().getId(),
                            teacherDetails.getCabin().getLaptopId())));
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE INSERTING STUDENT" +
                    " DETAILS OF NAME " + teacher.getName());
        }
    }

    /**
     * <p>
     * This method deals with assigning a random value for the laptopId.
     *
     * @return teacher
     * contains teacher id, name, handling subject, handling standard, handling section.
     */
    public int generateRandom() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    /**
     * <p>
     * This method retrieves all the teacher record from the database.
     * </p>
     *
     * @return all Teacher details in form of list to display it to the enduser.
     * @throws SchoolManagementException this occurs when anything went wrong while retrieving data.
     */
    public List<ViewTeacherDto> fetchTeachers() throws SchoolManagementException {
        try {
            List<Teacher> teachers = teacherRepository.findAll();
            if (!teachers.isEmpty()) {
                List<ViewTeacherDto> allTeachers = new ArrayList<>();
                for (Teacher teacher : teachers) {
                    allTeachers.add(new ViewTeacherDto(teacher));
                }
                return allTeachers;
            }
        } catch (Exception e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING ALL TEACHER DETAILS");
        }
        return null;
    }

    /**
     * <p>
     * This method retrieves a particular teacher record from the database based on the id (unique identifier represents each student).
     * </p>
     *
     * @param id the unique identifier of the teacher to be retrieved
     * @return the teacher object corresponding to the provided ID, or null if no such student is found
     * @throws SchoolManagementException this occurs when anything went wrong while searching a data.
     */
    public ResponseTeacherDto findTeacher(int id) throws SchoolManagementException {
        try {
            Optional<Teacher> searchedTeacher = teacherRepository.findById(id);
            if (searchedTeacher.isPresent()) {
                Set<ResponseGradeDto> grades = new HashSet<>();
                for (Grade grade : searchedTeacher.get().getGrades()) {
                    grades.add(new ResponseGradeDto(grade));
                }
                return (new ResponseTeacherDto(searchedTeacher.get(), grades));
            }
        } catch (Exception e) {
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE RETRIEVING THE TEACHER DETAILS OF ID " + id, e);
        }
        return null;
    }

    /**
     * <p>
     * This method deletes a teacher record from the database based on the provided teacher ID.
     * If the given id matches, it removes the teacher with the specified ID.
     * </p>
     *
     * @param id the unique identifier of the teacher to be deleted
     * @return true if the teacher is deleted successfully or else returns false.
     * @throws SchoolManagementException this occurs when anything went wrong while removing a data.
     */
    public boolean isDeleteTeacher(int id) throws SchoolManagementException {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}