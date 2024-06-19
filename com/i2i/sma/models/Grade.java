package com.i2i.sma.models;

import java.util.HashSet;
import java.util.Set;

/**
* <p>
* This class is responsible for maintaining methods to get and set the attributes such as standard, section, student and teacher.
* These attributes can be accessed throughout the application.
* </p>
*/
public class Grade {
    private int id;
    private int standard;
    private String section;
    private Set<Student> students = new HashSet<Student>(0);
    private Set<Teacher> teachers = new HashSet<Teacher>(0);

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setStandard(int standard) {
        this.standard = standard;
    }
    public int getStandard() {
        return standard;        
    }
    public void setSection(String section) {
        this.section = section;
    }
    public String getSection() {
         return section;  
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    public Set<Student> getStudents() {
         return students;  
    }
    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
    public Set<Teacher> getTeachers() {
         return teachers;  
    }

    // It prints the Grade details in string format.
    public String toString() {
        StringBuilder  gradeDetails = new StringBuilder();
        gradeDetails.append("\n\n\t\tGRADE DETAILS");
        gradeDetails.append("\nStandard: ").append(standard);
        gradeDetails.append("\nSection: ").append(section);
        gradeDetails.append("\nGrade ID: " ).append(id);
        return gradeDetails.toString();
    }
}