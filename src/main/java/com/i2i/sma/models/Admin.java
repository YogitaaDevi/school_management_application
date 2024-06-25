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
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // It prints the teacher details in string format.
    public String toString() {
        StringBuilder adminDetails = new StringBuilder();
        adminDetails.append("\n\n\t\tADMIN DETAILS ");
        adminDetails.append("\nAdmin Id: ").append(id);
        adminDetails.append("\nAdmin Name: ").append(name);
        //teacherDetails.append("\nHandling subject: ").append(subject);
        return adminDetails.toString();
    }
}