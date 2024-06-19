package com.i2i.sma.models;
import java.util.HashSet;
import java.util.Set;
/**
* <p>
* This class is responsible for maintaining methods to get and set the attributes such as teacher's id, name, subject, schoolId, schooldetails and cabindetails.
* These attributes can be accessed throughout the application.
* </p>
*/
public class Teacher {
    private int id;
    private String name;
    private String subject;
    private Cabin cabin;
    private Set<Grade> grades = new HashSet<Grade>(0);

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getSubject() {
        return subject;
    }
    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }
    public Cabin getCabin() {
        return cabin;
    }
    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
    public Set<Grade> getGrades() {
         return grades;  
    }

    // It prints the teacher details in string format.
    public String toString() {
        StringBuilder  teacherDetails = new StringBuilder();
        teacherDetails.append("\n\n\t\tTEACHER DETAILS ");
        teacherDetails.append("\nTeacher Id: ").append(id);
        teacherDetails.append("\nTeacher Name: ").append(name);
        teacherDetails.append("\nHandling subject: " ).append(subject);
        return teacherDetails.toString();
    }
}