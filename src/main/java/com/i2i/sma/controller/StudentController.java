package com.i2i.sma.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.sma.dto.RequestStudentDto;
import com.i2i.sma.dto.RequestStudentUpdateDto;
import com.i2i.sma.dto.ResponseStudentDto;
import com.i2i.sma.dto.ViewStudentDto;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.service.StudentServiceInterface;
import com.i2i.sma.utils.DataValidationUtil;
import com.i2i.sma.utils.DateUtil;

/**
 * <p>
 * This class is responsible for managing student records.
 * It provides functionalities to create and add new students, assign them to
 * specified standards and sections, display all students records,
 * search for students and remove students.
 * </p>
 */
@RestController
@RequestMapping("sma/api/v1/students")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentServiceInterface studentServiceInterface;

    /**
     * <p>
     * This method is responsible for adding a new student record with the specified grade.
     * Once the student is successfully added, it prints out the student's details and
     * a success message.
     * </p>
     * @param requestStudentDto {@link RequestStudentDto}
     * @return studentDetail {@link ResponseStudentDto}
     */
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody RequestStudentDto requestStudentDto) {
        try {
            if(!DataValidationUtil.validateString(requestStudentDto.getName())) {
                return new ResponseEntity<>("NAME MUST BE IN ALPHABETS (A-Z/a-z)"
                        , HttpStatus.BAD_REQUEST);
            }
            if (!DateUtil.isValidateDate(requestStudentDto.getDob())){
                return new ResponseEntity<>("DATE OF BIRTH MUST NOT BE GREATER THAN TODAY" +
                        " AND NOT LESSER THAN 20 YEARS FROM TODAY", HttpStatus.BAD_REQUEST);
            }
            if (!DataValidationUtil.checkNumberRange(requestStudentDto.getGrade().getStandard())) {
                return new ResponseEntity<>("STANDARD MUST BE IN NUMBER WITHIN 1-12"
                        , HttpStatus.BAD_REQUEST);
            }
            if (!DataValidationUtil.validateString(requestStudentDto.getGrade().getSection())) {
                return new ResponseEntity<>("SECTION MUST BE IN ALPHABETS (A-Z/a-z)"
                        , HttpStatus.BAD_REQUEST);
            }
            else {
                logger.debug("ADDING THE STUDENT DETAILS OF NAME: {} ", requestStudentDto.getName());
                ResponseStudentDto studentDetail = studentServiceInterface.addStudentToGrade(requestStudentDto);
                logger.info("STUDENT DETAILS OF NAME: {} AND ID: {} ADDED SUCCESSFULLY "
                        , studentDetail.getName(), studentDetail.getId());
                return new ResponseEntity<>(studentDetail, HttpStatus.CREATED);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>("SOMETHING WENT WRONG WHILE INSERTING STUDENT DETAILS "
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles displaying all students record.
     * It calls fetchStudent method and displays the all student details available in the Grade.
     * </p>
     * @return list of all students {@link ViewStudentDto}
     */
    @GetMapping
    public ResponseEntity<?> viewStudents() {
        try {
            List<ViewStudentDto> Details = studentServiceInterface.fetchStudents();
            if (null != Details) {
                logger.info("ALL STUDENTS DATA ARE DISPLAYED SUCCESSFULLY");
                return new ResponseEntity<>(Details, HttpStatus.OK);
            } else {
                logger.warn("NO STUDENTS FOUND IN DATABASE");
                return new ResponseEntity<>("NO STUDENT DATA FOUND", HttpStatus.NOT_FOUND);
            }
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles searching of students record based on the student id.
     * It gets id as a path variable parameter.
     * Then it retrieves data of corresponding student based on id and displays it.
     * If the given id is wrong, it displays a warning message.
     * For example: provide valid student id.
     * </p>
     * @param id
     *   a unique identifier that represents each student
     * @return searchedStudent {@link ResponseStudentDto} if the given id found. Else null.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> searchStudent(@PathVariable UUID id) {
        try {
            ResponseStudentDto searchedStudent = studentServiceInterface.findStudent(id);
            if (null != searchedStudent) {
                logger.info("STUDENT ID: {} FOUND SUCCESSFULLY", id);
                return new ResponseEntity<>( searchedStudent, HttpStatus.OK);
            } else {
                logger.warn("CANNOT FIND STUDENT OF ID: {}", id);
                return new ResponseEntity<>("NO SUCH STUDENT FOUND ON ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles updating of students record .
     * It gets data to update as a path variable parameter.
     * Then it updates data of corresponding student id and displays it.
     * If the given id is wrong, it displays a warning message.
     * For example: provide valid student id.
     * </p>
     * @param
     * id (a unique identifier for each student)
     * RequestStudentUpdateDto {@link RequestStudentUpdateDto}
     * @return updatedStudent {@link ResponseStudentDto} if the given id found. Else null
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody RequestStudentUpdateDto requestStudentUpdateDto,
                                           @PathVariable UUID id) {
        try {
            ResponseStudentDto updatedStudent = studentServiceInterface.upgradeStudent(id, requestStudentUpdateDto);
            if(null != updatedStudent) {
                return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("NO SUCH STUDENT FOUND ON ID: " +
                        id, HttpStatus.NOT_FOUND);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles deleting of students record based on the student id.
     * It gets id as a path variable parameter.
     * Then it deletes data of corresponding student id and display a success message.
     * If the given id is wrong, it displays a warning message.
     * For example: provide valid student id.
     * </p>
     * @param id
     *   a unique identifier that represents each student
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeStudent(@PathVariable UUID id) {
        try {
            if(studentServiceInterface.isDeleteStudent(id)){
                logger.info("\nSTUDENT ID " + id + " REMOVED SUCCESSFULLY");
                return new ResponseEntity<>("SUCCESSFULLY DELETED STUDENT OF ID: "
                        + id, HttpStatus.ACCEPTED);

            } else {
                logger.info( "\nERROR WHILE DELETING STUDENT ID " + id +
                        "\nPLEASE CHECK THE STUDENT ID PROPERLY");
                return new ResponseEntity<>("NO SUCH STUDENT FOUND ON ID: "
                        + id, HttpStatus.NOT_FOUND);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
