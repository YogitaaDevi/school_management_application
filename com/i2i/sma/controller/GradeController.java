package com.i2i.sma.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Student;
import com.i2i.sma.models.Teacher;
import com.i2i.sma.service.GradeService;
import com.i2i.sma.utils.DateUtil;
import com.i2i.sma.utils.DataValidationUtil;

/**
* <p> 
* This class is responsible for managing grade records.
* It provides functionalities to create and add new grades, assign them to 
* specified students and teachers, display all grade records, search for grades and remove grades.
* </p>
*/
public class GradeController {
    private static Scanner scanner = new Scanner(System.in);
    private GradeService gradeService = new GradeService();

    /**
    * <p>
    * This method handles displaying all grade record.
    * It calls fetchGradeDetails method and displays the all grade details.
    *</p>
    */
    public void viewGrades() {
        try {
            List<Grade> Details = gradeService.fetchGradeDetails();
            if (null != Details) {
                for (Grade grade : Details) {
                    System.out.println(grade);
                }
            } else {
                System.out.println("NO GRADES FOUND IN THE DATABASE");
            }
        } catch (SchoolManagementException e) { 
             System.out.println(e.getMessage());
        } 
    }

    /**
    * <p>
    * This method handles searching of Grade's specific standard and section record
    * along with the students. It prompts the user to enter the grade id they wish to see.
    * After getting id from the user, it retrieves data and display it to the user.
    * If the user given wrong id, it displays a warning message.
    * For example: provide valid grade id.
    *</p>
    */
    public void searchGrade() {
        System.out.println("Enter the ID to search: ");
        int id = scanner.nextInt();
        try {
            Grade searchedGradeDetails = gradeService.fetchGradeById(id);
            if (null != searchedGradeDetails) {
                System.out.println(searchedGradeDetails);
                System.out.println(searchedGradeDetails.getStudents());
                System.out.println(searchedGradeDetails.getTeachers());
            } else {
                System.out.println("THERE IS NO SUCH GRADE " + id + " EXIST ");
            }  
        } catch (SchoolManagementException e) { 
             System.out.println(e.getMessage());
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
    *</p>
    */
    public void removeGrade() {        
        System.out.println("Enter the grade ID to be deleted: ");
        int id = scanner.nextInt();
        try {
            System.out.println((gradeService.isDeleteGrade(id)) ? "\nGRADE ID " 
            + id + " IS REMOVED ALONG WITH THE STUDENTS PRESENT IN IT." 
            : "\nERROR WHILE DELETING GRADE ID " + id + "\nPLEASE CHECK THE GRADE ID PROPERLY");
        } catch (SchoolManagementException e) { 
             System.out.println(e.getMessage());
        } 
    }
}