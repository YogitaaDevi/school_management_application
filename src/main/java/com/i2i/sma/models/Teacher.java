package com.i2i.sma.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * <p>
 * This class is responsible for maintaining methods to get and set the attributes such as teacher's id, name, subject, schoolId and cabindetails.
 * These attributes can be accessed throughout the application.
 * </p>
 */
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "subject", nullable = false)
    private String subject;

    @OneToOne(cascade = CascadeType.ALL)
    private Cabin cabin;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "teacher_grade_association",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id")
    )
    private Set<Grade> grades = new HashSet<>(0);

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
        StringBuilder teacherDetails = new StringBuilder();
        teacherDetails.append("\n\n\t\tTEACHER DETAILS ");
        teacherDetails.append("\nTeacher Id: ").append(id);
        teacherDetails.append("\nTeacher Name: ").append(name);
        teacherDetails.append("\nHandling subject: ").append(subject);
        return teacherDetails.toString();
    }
}