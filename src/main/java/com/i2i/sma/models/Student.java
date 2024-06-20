package com.i2i.sma.models;

import java.time.LocalDate;

import com.i2i.sma.utils.DateUtil;

/**
* <p>
* This class is responsible for maintaining methods to get and set the attributes such as id, name, date of birth and school id.
* These attributes can be accessed throughout the application.
* </p>
*/
public class Student {
    private int id;
    private String name;
    private LocalDate dob;
    private Grade grade;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDob() {  
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    public Grade getGrade() {
        return grade;
    }

    // It prints the student details in string format.
    public String toString() {
        StringBuilder studentDetails = new StringBuilder();
        studentDetails.append("\n\n\t\tSTUDENT DETAILS ");
        studentDetails.append("\nStudent id: ").append(id);
        studentDetails.append("\nStudent Name: ").append(name);
        studentDetails.append("\nStudent Date of Birth: ").append(dob);
        studentDetails.append("\nStudent Age: ").append(DateUtil.calculateDifferenceBetweenDates(dob));
        return studentDetails.toString();
    }
}

