package com.i2i.sma.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.sma.dto.RequestTeacherDto;
import com.i2i.sma.dto.ResponseTeacherDto;
import com.i2i.sma.dto.ViewTeacherDto;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.service.TeacherService;

/**
 * <p>
 * This class is responsible for managing teacher records.
 * It provides functionalities to create and add new teachers, assign them to
 * specified grade, allocates a cabin, display all teachers records, search teacher and remove teacher.
 * </p>
 */
@RestController
@RequestMapping("sma/api/v1.0/teachers")
public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private TeacherService teacherService;

    /**
     * <p>
     * This method handles the add a new teacher record and allocates a cabin for each of them.
     * It prompts the user to enter the teacher's name, handling subject, standards and sections.
     * After collecting the information from the user, it validates whether the input from user
     * is in correct format.For example :
     * checks whether entered section contains a single letter alphabet,
     * checks whether entered standard contains only numbers.
     * After validations, it adds the teacher to the database.
     * Once the teacher is successfully added, it prints out the teacher's details and a success message.
     * </p>
     */
    @PostMapping
    public ResponseEntity<ResponseTeacherDto> addTeacher(@RequestBody RequestTeacherDto requestTeacherDto) {
        try {
            ResponseTeacherDto teacherDetails = teacherService.addNewTeacher(requestTeacherDto);
            logger.info("TEACHER DETAILS OF NAME: {} ADDED SUCCESSFULLY ", teacherDetails.getName());
            return new ResponseEntity<>(teacherDetails, HttpStatus.CREATED);
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles displaying all teacher record along with their allocated cabin details.
     * It calls fetchteacher method and displays the teacher details.
     */
    @GetMapping
    public ResponseEntity<List<ViewTeacherDto>> viewTeachers() {
        try {
            List<ViewTeacherDto> Details = teacherService.fetchTeachers();
            if (null != Details) {
                logger.info("ALL TEACHERS DATA ARE DISPLAYED SUCCESSFULLY");
                return new ResponseEntity<>(Details, HttpStatus.OK);
            } else {
                logger.warn("NO TEACHERS FOUND IN DATABASE");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles searching of teacher's record.
     * It prompts the user to enter the id of the teacher they wish to see the details.
     * After getting id from the user, it retrieves data and display it to the user.
     * If the user given wrong id, it displays a warning message.
     * For example: provide valid teacher id.
     * </p>
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTeacherDto> searchTeacher(@PathVariable int id) {
        try {
            ResponseTeacherDto searchedTeacher = teacherService.findTeacher(id);
            if (null != searchedTeacher) {
                logger.info("TEACHER ID: {} FOUND SUCCESSFULLY", id);
                return new ResponseEntity<>(searchedTeacher, HttpStatus.OK);
            } else {
                logger.warn("CANNOT FIND TEACHER ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles removing teachers along with their allocated cabin from record.
     * It prompts the user to enter the id of the teacher they wish to remove the details of.
     * After getting id from the user, it deletes the particular data.
     * After removing, it displays a successful message.
     * If the user given wrong id, it displays a warning message.
     * For example: provide valid teacher id.
     * </p>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTeacher(@PathVariable int id) {
        try {
            if (teacherService.isDeleteTeacher(id)) {
                logger.info("\nTEACHER ID " + id + " REMOVED SUCCESSFULLY");
                return new ResponseEntity<>("TEACHER DELETED SUCCESSFULLY",HttpStatus.ACCEPTED);

            } else {
                logger.info("\nERROR WHILE DELETING TEACHERID " + id +
                        "\nPLEASE CHECK THE ID PROPERLY");
                return new ResponseEntity<>("NO SUCH TEACHER FOUND",HttpStatus.NO_CONTENT);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}