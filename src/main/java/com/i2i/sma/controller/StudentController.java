package com.i2i.sma.controller;

import java.util.Scanner;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.sma.helper.HibernateConnection;
import com.i2i.sma.model.Student;

/**
* <p> 
* This class is responsible for managing student.
* It provides functionalities to create and add new students.
* It prompts the user to enter student's name, their father and mother name.
* After getting the details, it saves the details and prints the student details with their
* associated id(a unique identifier for each student) with the success message. 
* </p>
*/
public class StudentController {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String args[]) {
        StudentController studentController = new StudentController();
        System.out.println("\nEnter your name : ");
        String name = scanner.next();
        System.out.println("\nEnter your Father Name : ");
        String fatherName = scanner.next();
        System.out.println("\nEnter your Mother Name : ");
        String motherName = scanner.next();
        Student student = new Student();
        student.setName(name);
        student.setFatherName(fatherName);
        student.setMotherName(motherName);
        System.out.println(studentController.insertStudent(student));
        System.out.println("Student added successfully"); 
    }

    /**
    * <p>
    * This method makes use of the Hibernate query language inorder
    * to insert a new student record into the database.
    * </p>
    * @param student 
    *     the student contains two things - 1. name of the student in string.
    *                                            Only alphabets are allowed.
    *                                       1. father name of the student in string.
    *                                            Only alphabets are allowed.
    *                                       1. mother name of the student in string.
    *                                            Only alphabets are allowed.                        
    */
    public Student insertStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (HibernateException e) {
            HibernateConnection.rollbackTransaction(transaction);
            System.out.println("\nSOMETHING WENT WRONG WHILE INSERTING STUDENT DETAILS OF NAME " + student.getName());
        }
        return student;
    }
}
