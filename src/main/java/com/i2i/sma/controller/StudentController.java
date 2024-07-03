package com.i2i.sma.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
import com.i2i.sma.models.Student;
import com.i2i.sma.service.StudentService;
import com.i2i.sma.utils.DateUtil;
import com.i2i.sma.utils.DataValidationUtil;

/**
 * <p>
 * This class is responsible for managing student records.
 * It provides functionalities to create and add new students, assign them to
 * specified standards and sections, display all students records,
 * search for students and remove students.
 * </p>
 */
@RestController
@RequestMapping("sma/api/v1.0/students")
public class StudentController {
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService ;

    /**
     * <p>
     * This method handles the add a new student record with the specified grade.
     * It prompts the user to enter the student's name, date of birth (DOB), standard, and section.
     * After collecting the information from the user, it validates
     * whether the input from user is in correct format.
     * For example : checks whether entered name contains only alphabets,
     * checks whether entered dob is in correct format etc.
     * After validations, it adds the student to the database and
     * assigns the specified standard and section to the student.
     * Once the student is successfully added, it prints out the student's details and a success message.
     * </p>
     */
    @PostMapping("/addStudent")
    public Student addStudent() {
        String name;
        int standard;
        String section;
        String dob;
        while (true) {
            System.out.println("\nEnter your name : ");
            name = scanner.next();
            if (!DataValidationUtil.validateString(name)) {
                System.out.println("PLEASE ENTER A PROPER STUDENT NAME. MUST BE ONLY ALPHABETS");
                continue;
            }
            break;
        }
        while (true) {
            System.out.println("Enter your DOB as YYYY-MM-DD : ");
            dob = scanner.next();
            if (!DateUtil.isValidateDate(dob)) {
                System.out.println("PLEASE ENTER A PROPER DATE. MUST NOT BE ALPHABETS");
                continue;
            }
            break;
        }
        LocalDate dateOfBirth = LocalDate.parse(dob);
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
        try {
            Grade gradeDetail = studentService.getStudentGrade(standard, section);
            Student studentDetail = studentService.addStudentToGrade(name, dateOfBirth, gradeDetail);
            System.out.println(studentDetail);
            System.out.println(gradeDetail);
            System.out.println("\nStudent data has been added successfully");
            logger.info("STUDENT DETAILS OF NAME: {} ADDED SUCCESSFULLY ", name);
            return studentDetail;

        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);

        }
        return null;
    }

    /**
     * <p>
     * This method handles displaying all students record.
     * It calls fetchStudent method and displays the all student details available in the Grade.
     * </p>
     */
    @GetMapping("/viewStudents")
    public List<Student> viewStudents() {
        try {
            List<Student> Details = studentService.fetchStudents();
            if (null != Details) {
                for (Student student : Details) {
                    System.out.println(student);
                }
                logger.info("ALL STUDENTS DATA ARE DISPLAYED SUCCESSFULLY");
            } else {
                System.out.println("NO STUDENT DATA FOUND IN THE DATABASE");
                logger.warn("NO STUDENTS FOUND IN DATABASE");
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
     * This method handles searching of students record.
     * It prompts the user to enter the student id they wish to see the details.
     * After getting id from the user, it retrieves data and display it to the user.
     * If the user given wrong id, it displays a warning message.
     * For example: provide valid student id.
     * </p>
     */
    @GetMapping("/searchStudent/{id}")
    public Optional<Student> searchStudent(@PathVariable int id) {
        System.out.println("Enter the ID to search: ");
        try {
            Optional<Student> searchedStudent = studentService.findStudent(id);
            if (searchedStudent.isPresent()) {
                System.out.println(searchedStudent);
                logger.info("STUDENT ID: {} FOUND SUCCESSFULLY", id);
            } else {
                System.out.println("THERE IS NO SUCH STUDENT " + id + " EXIST ");
                logger.warn("CANNOT FIND STUDENT OF ID: {}", id);
            }
            return searchedStudent;
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);

        }
        return null;
    }

    /**
     * <p>
     * This method handles removing students from record.
     * It prompts the user to enter the id of the student they wish to remove the details.
     * After getting id from the user, it deletes the particular data.
     * After removing, it displays a successful message.
     * If the user given wrong id, it displays a warning message.
     * For example: provide valid student id.
     * </p>
     */
    @DeleteMapping("/deleteStudent/{id}")
    public void removeStudent(@PathVariable int id) {
        System.out.println("Enter the ID to delete: ");
        try {
            System.out.println((studentService.isDeleteStudent(id)) ? "\nSTUDENT ID "
                    + id + " REMOVED SUCCESSFULLY" : "\nERROR WHILE DELETING STUDENT ID "
                    + id + "\nPLEASE CHECK THE STUDENT ID PROPERLY");
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }
}
