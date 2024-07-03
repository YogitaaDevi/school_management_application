package com.i2i.sma.controller;

import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Teacher;
import com.i2i.sma.service.TeacherService;
import com.i2i.sma.utils.DataValidationUtil;
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
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private TeacherService teacherService ;

    /**
     * <p>
     * This method handles the add a new teacher record and allocates a cabin for each of them.
     * It prompts the user to enter the teacher's name, handling subject, standards and sections.
     * After collecting the information from the user, it validates whether the input from user
     * is in correct format.For example :
     *         checks whether entered section contains a single letter alphabet,
     *         checks whether entered standard contains only numbers.
     * After validations, it adds the teacher to the database.
     * Once the teacher is successfully added, it prints out the teacher's details and a success message.
     * </p>
     */
    @PostMapping("/addTeacher")
    public Teacher addTeacher() {
        String name;
        String subject;
        int standard;
        String section;
        Set<Grade> grades = new HashSet<>(0);
        while (true) {
            System.out.println("Enter the teacher name: ");
            name = scanner.next();
            if (!DataValidationUtil.validateString(name)) {
                System.out.println("PLEASE ENTER A PROPER TEACHER NAME. MUST BE ONLY ALPHABETS");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Enter the teacher's subject: ");
            subject = scanner.next();
            if (!DataValidationUtil.validateString(subject)) {
                System.out.println("PLEASE ENTER THE PROPER SUBJECT NAME. MUST BE ONLY ALPHABETS");
                continue;
            }
            break;
        }
        try {
            while (true) {
                System.out.println("Enter the class : ");
                standard = scanner.nextInt();
                while (true) {
                    System.out.println("Enter your Section : ");
                    section = scanner.next();
                    if (!DataValidationUtil.validateString(section)) {
                        System.out.println("PLEASE ENTER A VALID SECTION. MUST BE ONLY ALPHABETS");
                        continue;
                    }
                    break;
                }
                Grade gradeDetails = teacherService.getTeacherGrade(standard, section);
                grades.add(gradeDetails);
                System.out.println("\nDo you handle any other grade: YES or NO ");
                String handlingGrade = scanner.next();
                if (handlingGrade.equals("YES")) {
                    continue;
                } else if (handlingGrade.equals("NO")) {
                    break;
                }
            }
            Teacher teacherDetails = teacherService.addNewTeacher(name, subject, grades);
            System.out.println(teacherDetails);
            System.out.println("\nTeacher data has been added successfully");
            logger.info("TEACHER DETAILS OF NAME: {} ADDED SUCCESSFULLY ", name);
            return teacherDetails;
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * <p>
     * This method handles displaying all teacher record along with their allocated cabin details.
     * It calls fetchteacher method and displays the teacher details.
     */
    @GetMapping("/viewTeachers")
    public List<Teacher> viewTeachers() {
        try {
            List<Teacher> Details = teacherService.fetchTeachers();
            if (null != Details) {
                for (Teacher teacher : Details) {
                    System.out.println(teacher);
                }
                logger.info("ALL TEACHERS DATA ARE DISPLAYED SUCCESSFULLY");
            } else {
                System.out.println("NO TEACHER DATA FOUND IN THE DATABASE");
                logger.warn("NO TEACHERS FOUND IN DATABASE");
            }
            return Details;
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return null;
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
    @PostMapping("/{id}")
    public Optional<Teacher> searchTeacher(@PathVariable int id) {
        System.out.println("Enter the ID to search: ");
        try {
            Optional<Teacher> searchedTeacher = teacherService.findTeacher(id);
            if (searchedTeacher.isPresent()) {
                System.out.println(searchedTeacher);
                logger.info("TEACHER ID: {} FOUND SUCCESSFULLY", id);
            } else {
                System.out.println("THERE IS NO SUCH TEACHER " + id + " EXIST ");
                logger.warn("CANNOT FIND TEACHER ID: {}", id);
            }
            return searchedTeacher;
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return null;
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
    @DeleteMapping("/deleteTeacher/{id}")
    public void removeTeacher(@PathVariable int id) {
        System.out.println("Enter the ID to delete: ");
        try {
            System.out.println((teacherService.isDeleteTeacher(id)) ? "\nTEACHER ID " + id +
                    " IS REMOVED SUCCESSFULLY ALONG WITH THEIR ASSOCIATED CABIN."
                    : "\nERROR WHILE DELETING TEACHER ID " + id +
                    "\nPLEASE CHECK THE TEACHER ID PROPERLY");
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }
}