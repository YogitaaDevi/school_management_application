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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.sma.dto.ResponseGradeDto;
import com.i2i.sma.dto.ViewGradeDto;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.service.GradeService;

@RestController
@RequestMapping("sma/api/v1.0/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);

    /**
     * <p>
     * This method handles displaying all grade record.
     * It calls fetchGradeDetails method and displays the all grade details.
     * </p>
     */
    @GetMapping
    public ResponseEntity<List<ResponseGradeDto>> viewGrades() {
        try {
            List<ResponseGradeDto> Details = gradeService.fetchGradeDetails();
            if (null != Details) {
                logger.info("ALL GRADES DATA ARE DISPLAYED SUCCESSFULLY");
                return (new ResponseEntity<>(Details, HttpStatus.OK));
            } else {
                logger.warn("NO GRADES FOUND IN DATABASE");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles searching of Grade's specific standard and section record
     * along with the students. It prompts the user to enter the grade id they wish to see.
     * After getting id from the user, it retrieves data and display it to the user.
     * If the user given wrong id, it displays a warning message.
     * For example: provide valid grade id.
     * </p>
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> searchGrade(@PathVariable int id) {
        try {
            ViewGradeDto searchedGradeDetails = gradeService.fetchGradeById(id);
            if (null != searchedGradeDetails) {
                logger.info("GRADE ID: {} FOUND SUCCESSFULLY", id);
                return new ResponseEntity<>(searchedGradeDetails, HttpStatus.OK);
            } else {
                logger.warn("CANNOT FIND GRADE ID: {}", id);
                return new ResponseEntity<>("NO SUCH GRADE FOUND ON ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * This method handles removing standard and section along with students who are assigned to it.
     * It prompts the user to enter grade id they wish to remove the details.
     * After getting the input, it deletes the particular data.
     * After removing, it displays a successful message.
     * If the user given wrong standard and section, it displays a warning message.
     * For example: provide valid grade id.
     * </p>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeGrade(@PathVariable int id) {
        try {
            if(gradeService.isDeleteGrade(id)){
                logger.info("\nGRADE ID " + id + " REMOVED SUCCESSFULLY");
                return new ResponseEntity<>("SUCCESSFULLY DELETED OF GRADE ID: " + id, HttpStatus.ACCEPTED);
            } else {
                logger.info( "\nERROR WHILE DELETING GRADE ID " + id +
                        "\nPLEASE CHECK THE ID PROPERLY");
                return new ResponseEntity<>("NO SUCH GRADE FOUND ON ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (SchoolManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
