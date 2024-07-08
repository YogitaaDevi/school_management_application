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

import com.i2i.sma.dto.RequestTeacherDto;
import com.i2i.sma.dto.RequestTeacherUpdateDto;
import com.i2i.sma.dto.ResponseTeacherDto;
import com.i2i.sma.dto.ViewTeacherDto;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.service.TeacherServiceInterface;
import com.i2i.sma.utils.DataValidationUtil;

/**
 * <p>
 * This class is responsible for managing teacher records.
 * It provides functionalities to create and add new teachers, assign them to
 * specified grade, allocates a cabin, display all teachers records, search teacher and remove teacher.
 * </p>
 */
@RestController
@RequestMapping("sma/api/v1/teachers")
public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private TeacherServiceInterface teacherServiceInterface;

    /**
     * <p>
     * This method handles the add a new teacher record and allocates a cabin for each of them.
     * Once the teacher is successfully added, it prints out the teacher's details and a success message.
     * </p>
     * @param requestTeacherDto {@link ResponseTeacherDto}
     * @return teacherDetails {@link ResponseTeacherDto}
     */
    @PostMapping
    public ResponseEntity<?> addTeacher(@RequestBody RequestTeacherDto requestTeacherDto) {
        try {
            if(!DataValidationUtil.validateString(requestTeacherDto.getName())) {
                return new ResponseEntity<>("NAME MUST BE IN ALPHABETS (A-Z/a-z)", HttpStatus.BAD_REQUEST);
            }
            if (!DataValidationUtil.validateString(requestTeacherDto.getSubject())) {
                return new ResponseEntity<>("SUBJECT MUST BE IN ALPHABETS (A-Z/a-z)", HttpStatus.BAD_REQUEST);
            }
            else{
                logger.debug("ADDING THE TEACHER DETAILS OF NAME: {}", requestTeacherDto.getName());
                ResponseTeacherDto teacherDetails = teacherServiceInterface.addNewTeacher(requestTeacherDto);
                logger.info("TEACHER DETAILS OF NAME: {} AND ID: {} ADDED SUCCESSFULLY ", teacherDetails.getName(), teacherDetails.getId());
                return new ResponseEntity<>(teacherDetails, HttpStatus.CREATED);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles displaying all teacher record along with their allocated cabin details.
     * It calls fetchteacher method and displays the teacher details.
     * @return list of all teachers {@link ViewTeacherDto}
     */
    @GetMapping
    public ResponseEntity<?> viewTeachers() {
        try {
            List<ViewTeacherDto> Details = teacherServiceInterface.fetchTeachers();
            if (null != Details) {
                logger.info("ALL TEACHERS DATA ARE DISPLAYED SUCCESSFULLY");
                return new ResponseEntity<>(Details, HttpStatus.OK);
            } else {
                logger.warn("NO TEACHERS FOUND IN DATABASE");
                return new ResponseEntity<>("NO TEACHER DATA FOUND", HttpStatus.NOT_FOUND);
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
     * It gets id as a path variable parameter.
     * After getting id, it retrieves data and display it to the user.
     * If the user given wrong id, it displays a warning message.
     * For example: provide valid teacher id.
     * </p>
     * @param id a unique identifier that represents each teacher
     * @return searchedTeacher {@link ResponseTeacherDto} if the given id found
     * Else null
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> searchTeacher(@PathVariable UUID id) {
        try {
            ResponseTeacherDto searchedTeacher = teacherServiceInterface.findTeacher(id);
            if (null != searchedTeacher) {
                logger.info("TEACHER ID: {} FOUND SUCCESSFULLY", id);
                return new ResponseEntity<>(searchedTeacher, HttpStatus.OK);
            } else {
                logger.warn("CANNOT FIND TEACHER ID: {}", id);
                return new ResponseEntity<>("NO SUCH TEACHER FOUND ON ID: "
                        + id, HttpStatus.NOT_FOUND);
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
     * id (a unique identifier for each teacher)
     * RequestTeacherUpdateDto {@link RequestTeacherUpdateDto}
     * @return updatedTeacher {@link ResponseTeacherDto} if the given id found. Else null
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable UUID id,
                                           @RequestBody RequestTeacherUpdateDto requestTeacherUpdateDto) {
        try {
            ResponseTeacherDto updatedTeacher = teacherServiceInterface.upgradeTeacher(id, requestTeacherUpdateDto);
            if(null != updatedTeacher) {
                return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("NO SUCH TEACHER FOUND ON ID: " +
                        id, HttpStatus.NOT_FOUND);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles removing teachers along with their allocated cabin from record.
     * It gets id as a path variable parameter.
     * After getting id, it deletes the particular data.
     * After removing, it displays a successful message.
     * If the user given wrong id, it displays a warning message.
     * For example: provide valid teacher id.
     * </p>
     * @param id a unique identifier that represents each teacher
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeTeacher(@PathVariable UUID id) {
        try {
            if (teacherServiceInterface.isDeleteTeacher(id)) {
                logger.info("\nTEACHER ID " + id + " REMOVED SUCCESSFULLY");
                return new ResponseEntity<>("SUCCESSFULLY DELETED TEACHER OF ID: " + id,HttpStatus.ACCEPTED);

            } else {
                logger.info("\nERROR WHILE DELETING TEACHER ID " + id +
                        "\nPLEASE CHECK THE ID PROPERLY");
                return new ResponseEntity<>("NO SUCH TEACHER FOUND ON ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}