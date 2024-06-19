package com.i2i.sma.model;

/**
* <p>
* This class is responsible for maintaining methods to get and set the attributes such as id, name, father name and mother name.
* These attributes can be accessed throughout the application.
* </p>
*/
public class Student {
    private int id;
    private String name;
    private String fatherName;
    private String motherName;

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
    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public String getMotherName() {
        return motherName;
    }
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }


    // It prints the student details in string format.
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\n\t\tSTUDENT DETAILS");
        stringBuilder.append("\nStudent id: ").append(id);
        stringBuilder.append("\nStudent name: ").append(name);
        stringBuilder.append("\nFather name: ").append(fatherName);
        stringBuilder.append("\nMother name: ").append(motherName);
        return stringBuilder.toString();
    }
}

