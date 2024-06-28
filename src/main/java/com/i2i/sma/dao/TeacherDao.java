package com.i2i.sma.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.helper.HibernateConnection;
import com.i2i.sma.models.Cabin;
import com.i2i.sma.models.Teacher;

/**
 * <p>
 * This class is responsible for managing teacher details.
 * It provides functionalities of following:
 * 1.Check the availablility of teacher and add details.
 * 2.Add new teacher details, fetch all teacher details and
 * 3.Deletes a particular teacher along with their associated cabin.
 * </p>
 */
public class TeacherDao {
    private static final Logger logger = LoggerFactory.getLogger(TeacherDao.class);
    

    /**
     * <p>
     * This method makes use of the Hibernate query language inorder
     * to insert a new teacher record into the database.
     * </p>
     *
     * @param teacher
     *   contains three things - 1. name of the teacher in string. Only alphabets are allowed.
     *                           2. subject handled by the teacher in string. Only alphabets are allowed.
     *                           3. grade that the teacher is going to handle.
     * @throws SchoolManagementException
     *   this happens when something goes wrong during data insertion.
     */
    public void insertTeacherDetails(Teacher teacher) throws SchoolManagementException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            logger.debug("PROCESS STARTED: INSERTING TEACHER DETAILS OF NAME: {} IN THE DATABASE",
                    teacher.getName());
            transaction = session.beginTransaction();
            session.save(teacher);
            transaction.commit();
        } catch (HibernateException e) {
            HibernateConnection.rollbackTransaction(transaction);
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE INSERTING TEACHER DETAILS" +
                    " OF NAME " + teacher.getName());
        }
    }

    /**
     * <p>
     * This method makes use of the Hibernate query language inorder
     * to display all teachers record from the database.
     * </p>
     *
     * @return
     *   all teachers data along with their associated cabin details.
     * @throws SchoolManagementException
     *   this happens when something goes wrong during data retrieving.
     */
    public List<Teacher> getDetails() throws SchoolManagementException {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            logger.debug("PROCESS STARTED: FETCHING ALL TEACHER DETAILS FROM THE DATABASE");
            return session.createQuery("FROM Teacher", Teacher.class).list();
        } catch (HibernateException e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE RETRIEVING THE TEACHER DATA");
        }
    }

    /**
     * <p>
     * This method makes use of the Hibernate query language inorder
     * to search and display for a particular teacher record.
     * It is done with the help of id (a unique identifier represent each students)
     * </p>
     * @param id
     *   a unique identifier for each teacher in integer.
     * @return
     *   Teacher data if the id is available. Else returns null.
     * @throws SchoolManagementException
     *   this happens when something goes wrong during searching for a data.
     */
    public Teacher findTeacherById(int id) throws SchoolManagementException {
        Teacher teacher = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            logger.debug("PROCESS STARTED: FETCHING A TEACHER DETAILS OF ID: {} FROM THE DATABASE"
                     , id);
            teacher = session.get(Teacher.class, id);
            if (null != teacher) {
                Hibernate.initialize(teacher.getGrades());
            }
            return teacher;
        } catch (HibernateException e) {
            throw new SchoolManagementException("SOMETHING WENT WRONG WHILE FETCHING THE TEACHER " +
                    "DETAILS OF ID " + id + ".\nPLEASE TRY AGAIN");
        }
    }

    /**
     * <p>
     * This method makes use of the Hibernate query language
     * to delete a particular teacher record from the teacher table along with cabin allocated for them.
     * It is done with the help of id (a unique identifier represent each teachers).
     * </p>
     *
     * @param id
     *   a unique identifier for each teacher in integer.
     * @return
     *   true if the particular teacher record is deleted.Else returns false.
     * @throws SchoolManagementException
     *   this happens when something goes wrong during data removing.
     */
    public boolean isRemoveTeacher(int id) throws SchoolManagementException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            logger.debug("PROCESS STARTED: REMOVING A TEACHER DETAILS OF ID: {} FROM THE DATABASE" , id);
            transaction = session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, id);
            if (null != teacher) {
                session.delete(teacher);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
            HibernateConnection.rollbackTransaction(transaction);
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE REMOVING THE TEACHER ID " 
                    + id + ".\nCHECK THE QUERY AND TRY AGAIN");
        }
    }
}