package com.i2i.sma.service;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.i2i.sma.dao.TeacherDao;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Cabin;
import com.i2i.sma.models.Teacher;
import com.i2i.sma.service.GradeService;

/**
* <p>
* This class is responsible for managing teacher details, their associated standard and section.
* It provides functionalities to add teacher details with their cabin details
* </p>
*/

public class TeacherService {
    private TeacherDao teacherDao = new TeacherDao();

    /**
    *<p>
    * This method deals with assigning the teacher in their associated standard and section.
    * It takes the standard, section and grade details .
    * This method provides the following functionalities:
    * 1.It creates a teacher with their name, subject and grade.
    * 2.Then it creates a cabin for a particular teacher along with the laptopId.
    * @param name.     
    *      teacher's name in string. Only alphabets are allowed
    * @param subject.     
    *         teacher's handling subject in string. Only alphabets are allowed
    * @param grade.     
    *     contains standard and section along with their grade id that the teacher is associated with it.
    * @return teacher
    *      contains teacher id, name, handling subject, handling standard, handling section.
    * @throws SchoolManagementException 
    *      this occurs when anything went wrong while adding teacher details.
    */
    public Teacher addNewTeacher(String name, String subject, Set<Grade> grades) throws SchoolManagementException { 
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setSubject(subject);
        teacher.setGrades(grades);
        Cabin cabin = new Cabin();
        cabin.setLaptopId(generateRandom());
        teacher.setCabin(cabin);
        teacherDao.insertTeacherDetails(teacher);
        return teacher;
    }

    /**
    *<p>
    * This method provides grade details of the specified standard and section to the database. It takes the student's
    * standard, and section as parameters. The method performs the following steps:
    * 1. It checks if the specified standard and section is present.
    * 2. If present, it returns the grade details.
    * 3. If not present, it adds the particular standard and section to the database and returns that grade details.
    *</p>
    * @param standard 
    *     the standard of the grade in numerical. Only 1-12 numbers are acceptable.
    * @param section 
    *     the section of the grade in string. Only alphabets are allowed
    * @return gradeDetails.
    *     that contains: 1. id of the grade(a unique identifier represents each grade)
    *                    2. standard of the grade in numerical.
    *                    3. the section of the grade in string.
    * @throws SchoolManagementException 
    *      this occurs when anything went wrong while inserting a data.
    */
    public Grade getTeacherGrade(int standard, String section) throws SchoolManagementException {
        GradeService gradeService = new GradeService();
        Grade gradeDetails = gradeService.getGrade(standard, section);        
        if(null == gradeDetails) {
            Grade grade = new Grade();
            grade.setStandard(standard);
            grade.setSection(section); 
            gradeDetails = gradeService.addGrade(grade);
        }
        return gradeDetails;
    }

    /**
    *<p>
    * This method deals with assigning a random value for the laptopId.
    * @return teacher
    *      contains teacher id, name, handling subject, handling standard, handling section.
    */
    public int generateRandom() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    /**
    *<p>
    * This method retrieves all the teacher record from the database.
    *</p>
    * @return all Teacher details in form of list to display it to the enduser.
    * @throws SchoolManagementException 
    *      this occurs when anything went wrong while retrieving data.
    */
    public List<Teacher> fetchTeachers() throws SchoolManagementException {
        return teacherDao.getDetails();
    }

    /**
    *<p>
    * This method retrieves a particular teacher record from the database based on the id (unique identifier represents each student).
    *</p>
    * @param id
    *     the unique identifier of the teacher to be retrieved
    * @return the teacher object corresponding to the provided ID, or null if no such student is found
    * @throws SchoolManagementException 
    *      this occurs when anything went wrong while searching a data.
    */
    public Teacher findTeacher (int id) throws SchoolManagementException {
        return teacherDao.findTeacherById(id);
    }

    /**
    *<p>
    * This method deletes a teacher record from the database based on the provided teacher ID.
    * If the given id matches, it removes the teacher with the specified ID.
    *</p>
    * @param id 
    *     the unique identifier of the teacher to be deleted
    * @return  
    *     returns true if the teacher is deleted successfully or else returns false.
    * @throws SchoolManagementException 
    *      this occurs when anything went wrong while removing a data. 
    */
    public boolean isDeleteTeacher(int id) throws SchoolManagementException {
        return teacherDao.isRemoveTeacher(id);
    }
}