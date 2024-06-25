package com.i2i.sma.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * <p>
 * This class is responsible for maintaining methods to get and set the attributes such as standard, section, student and teacher.
 * These attributes can be accessed throughout the application.
 * </p>
 */

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id", nullable = false)
    private int id;

    @Column(name = "standard", nullable = false)
    private int standard;

    @Column(name = "section", nullable = false)
    private String section;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>(0);
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "teacher_grade_association",
            joinColumns = @JoinColumn(name = "grade_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teachers = new HashSet<>(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }
    public void setSection(String section) {
        this.section = section;
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
        StringBuilder gradeDetails = new StringBuilder();
        gradeDetails.append("\n\n\t\tGRADE DETAILS");
        gradeDetails.append("\nStandard: ").append(standard);
        gradeDetails.append("\nSection: ").append(section);
        gradeDetails.append("\nGrade ID: ").append(id);
        return gradeDetails.toString();
    }
}