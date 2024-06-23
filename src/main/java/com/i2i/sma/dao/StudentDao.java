package com.i2i.sma.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.helper.HibernateConnection;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Student;


/**
 * <p>
 * This class is responsible for managing student records.
 * It provides functionalities to add new students to the list, fetch all student records, search for students, and remove students from the list.
 * </p>
 */
public class StudentDao {

    /**
     * <p>
     * This method makes use of the Hibernate query language inorder to insert a new student record into the database.
     * </p>
     *
     * @param student the student contains three things - 1. name of the student in string. Only alphabets are allowed.
     *                2. date of birth of the student in string.
     *                3. grade of the student.
     * @throws SchoolManagementException this happens when something goes wrong during data insertion.
     */
    public void insertStudent(Student student) throws SchoolManagementException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (HibernateException e) {
            HibernateConnection.rollbackTransaction(transaction);
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE INSERTING STUDENT DETAILS OF NAME " + student.getName());
        }
    }

    /**
     * <p>
     * This method makes use of Hibernate query language to retrieve all student records from the database.
     * </p>
     *
     * @return list of all student data that contains id, name, date of birth and grade id.
     * @throws SchoolManagementException this happens when something goes wrong during data retrieving.
     */
    public List<Student> getDetails() throws SchoolManagementException {
        List<Student> students = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            students = session.createQuery("FROM Student", Student.class).list();
            for (Student student : students) {
                Hibernate.initialize(student.getGrade());
            }
            return students;
        } catch (HibernateException e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING STUDENT DETAILS");
        }
    }

    /**
     * <p>
     * This method makes use of Hibernate query language to retrieve a particular student record from the database using the student ID.
     * </p>
     *
     * @param id a unique identifier for each student in integer.
     * @return Student data along with their class details if the ID is available. Else returns a empty student.
     * @throws SchoolManagementException this happens when something goes wrong during searching for a data.
     */
    public Student findStudentById(int id) throws SchoolManagementException {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        } catch (HibernateException e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING THE STUDENT ID " + id + " DETAILS.\nPLEASE TRY AGAIN...");
        }
    }

    /**
     * <p>
     * This method makes use of Hibernate query language to delete a particular student record from the database using the student ID.
     * </p>
     *
     * @param id a unique identifier for each student in integer.
     * @return returns true if the student was successfully deleted or else returns false.
     * @throws SchoolManagementException this happens when something goes wrong during data removing.
     */
    public boolean isRemoveStudent(int id) throws SchoolManagementException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (null != student) {
                session.delete(student);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
            HibernateConnection.rollbackTransaction(transaction);
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE REMOVING THE STUDENT ID " + id + "DETAILS.\nPLEASE TRY AGAIN...", e);
        }
    }
}
