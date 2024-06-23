package com.i2i.sma.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Teacher;
import com.i2i.sma.service.TeacherService;
import com.i2i.sma.utils.DateUtil;
import com.i2i.sma.utils.DataValidationUtil;

/**
 * <p>
 * This class is responsible for managing teacher records.
 * It provides functionalities to create and add new teachers, assign them to
 * specified grade, allocates a cabin, display all teachers records, search teacher and remove teacher.
 * </p>
 */
public class TeacherController {
    private static Scanner scanner = new Scanner(System.in);
    private TeacherService teacherService = new TeacherService();

    /**
     * <p>
     * This method handles the add a new teacher record and allocates a cabin for each of them.
     * It prompts the user to enter the teacher's name, handling subject, for which standard and section.
     * After collecting the information from the user, it validates whether the input from user is in correct format.
     * For example : checks whether entered section contains a single letter alphabet,
     * checks whether entered standard contains only numbers.
     * After validations, it adds the teacher to the database.
     * Once the teacher is successfully added, it prints out the teacher's details and a success message.
     * </p>
     */
    public void addTeacher() {
        String name;
        String subject;
        int standard;
        String section;
        Set<Grade> grades = new HashSet<Grade>(0);
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
            System.out.println(teacherService.addNewTeacher(name, subject, grades));
            System.out.println("\nTeacher data has been added successfully");
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * <p>
     * This method handles displaying all teacher record along with their allocated cabin details.
     * It calls fetchteacher method and displays the teacher details.
     */
    public void viewTeachers() {
        try {
            List<Teacher> Details = teacherService.fetchTeachers();
            if (null != Details) {
                for (Teacher teacher : Details) {
                    System.out.println(teacher);
                }
            } else {
                System.out.println("NO TEACHER DATA FOUND IN THE DATABASE");
            }
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
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
    public void searchTeacher() {
        System.out.println("Enter the ID to search: ");
        int id = scanner.nextInt();
        try {
            Teacher searchedTeacher = teacherService.findTeacher(id);
            if (null != searchedTeacher) {
                System.out.println(searchedTeacher);
                System.out.println(searchedTeacher.getCabin());
                System.out.println(searchedTeacher.getGrades());
            } else {
                System.out.println("THERE IS NO SUCH TEACHER " + id + " EXIST ");
            }
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
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
    public void removeTeacher() {
        System.out.println("Enter the ID to delete: ");
        int id = scanner.nextInt();
        try {
            System.out.println((teacherService.isDeleteTeacher(id)) ? "\nTEACHER ID "
                    + id + " IS REMOVED SUCCESSFULLY ALONG WITH THEIR ASSOCIATED CABIN."
                    : "\nERROR WHILE DELETING TEACHER ID " + id + "\nPLEASE CHECK THE TEACHER ID PROPERLY");
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
        }
    }
}