package com.i2i.sma.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.i2i.sma.utils.DateUtil;


/**
 * <p>
 * This class is responsible for maintaining methods to get and set the attributes such as id, name, date of birth and school id.
 * These attributes can be accessed throughout the application.
 * </p>
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "group_id", nullable = false)
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

