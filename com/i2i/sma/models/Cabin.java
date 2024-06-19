package com.i2i.sma.models;

/**
* <p>
* This class is responsible for maintaining methods to get and set the attributes such as teacher details, cabin id and laptop id.
* These attributes can be accessed throughout the application.
* </p>
*/

public class Cabin {
    private Teacher teacher;
    private int id;
    private int laptopId;
    
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
        StringBuilder  cabinDetails = new StringBuilder();
        cabinDetails.append("\n\n\t\tCABIN DETAILS ");
        cabinDetails.append("\nCabin ID: ").append(id);
        cabinDetails.append("\nLaptop ID: ").append(laptopId);
        return cabinDetails.toString();
    }
}