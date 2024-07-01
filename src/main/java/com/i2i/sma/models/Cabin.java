package com.i2i.sma.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * <p>
 * This class is responsible for maintaining methods to get and set the attributes such as teacher details, cabin id and laptop id.
 * These attributes can be accessed throughout the application.
 * </p>
 */
@Entity
@Table(name = "cabins")
public class Cabin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "laptop_id", nullable = false)
    private int laptopId;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLaptopId(int laptopId) {
        this.laptopId = laptopId;
    }

    public int getLaptopId() {
        return laptopId;
    }

    // It prints the cabin details in string format.
    public String toString() {
        StringBuilder cabinDetails = new StringBuilder();
        cabinDetails.append("\n\n\t\tCABIN DETAILS ");
        cabinDetails.append("\nCabin ID: ").append(id);
        cabinDetails.append("\nLaptop ID: ").append(laptopId);
        return cabinDetails.toString();
    }
}