package com.i2i.sma.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.i2i.sma.utils.DateUtil;
@Entity
@Table(name = "students")
public class Student {

    public Student() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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
    public LocalDate getDob() {
        return dob;
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

