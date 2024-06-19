package com.i2i.sma.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.helper.HibernateConnection;
import com.i2i.sma.models.Grade;
import com.i2i.sma.models.Student;

/**
* <p>
* This class is responsible for managing standards, sections
* and their associated students in the grade table.
* It provides functionalities of following:
* 1.Check the availability of standards and sections.
* 2.Add new standard and section details, fetch all the standard and section records.
* 3.Deletes a particular standard and section along with the students associated with it.
* </p>
*/
public class GradeDao {

    /**
    * <p>
    * This method makes use of the Hibernate query language inorder to insert a new grade record into the database.
    * </p>
    * @param grade 
    *     the grade contains two things - 1. standard of the grade in integer. Only 1-12 numbers are acceptable.
    *                                     2. section of the grade in string. Only alphabets are allowed.
    * @throws SchoolManagementException 
    *      this happens when something goes wrong during data insertion.
    */
    public Grade insertGrade(Grade grade) throws SchoolManagementException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(grade);
            transaction.commit();
        } catch (HibernateException e) {
            HibernateConnection.rollbackTransaction(transaction);
            throw new SchoolManagementException("Something went wrong while inserting grade details" , e);
        }
        return grade;
    }

    /**
    * <p>
    * This method makes use of Hibernate query language to get specific standard and section details.
    * It returns the particular grade with its id(a unique identifier represents each grade).
    * </p>
    * @param standard 
    *     the class standard of the grade in numerical. Only 1-12 numbers are acceptable.
    * @param section  
    *     the section of the grade in string. Only alphabets are allowed.
    * @return Grade
    *     that contains: 1. standard of the grade in integer. Only 1-12 numbers are acceptable.
    *                    2. section of the grade in string. Only alphabets are allowed.
    *                    3. id (a unique identifier represents each grade).
    * @throws SchoolManagementException 
    *      this happens when something goes wrong during data fectching.
    */
    public Grade fetchGrade(int standard, String section) throws SchoolManagementException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Grade> query = session.createQuery("FROM Grade WHERE standard = :standard AND section = :section", Grade.class);
            query.setParameter("standard", standard);
            query.setParameter("section", section);
            transaction.commit();
            return query.uniqueResult();
         } catch (NonUniqueResultException e) {
             HibernateConnection.rollbackTransaction(transaction);
             throw new SchoolManagementException("Multiple grades found for standard " + standard + " and section " + section, e);
         } catch (HibernateException e) {
             HibernateConnection.rollbackTransaction(transaction);
             throw new SchoolManagementException("Something went wrong while checking the grade details of standard " + standard + " and section " + section, e);
         }
     }

    /**
    * <p>
    * This method uses Hibernate to retrieve all the records of standards and sections available in the Grade table.
    * </p>
    * @return list of all standards and sections along with their grade id available in the grade table.
    * @throws SchoolManagementException 
    *      this happens when something goes wrong during fetching data.
    */
    public List<Grade> getDetails() throws SchoolManagementException {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            return session.createQuery("FROM Grade").list();
        } catch (HibernateException e) {
            throw new SchoolManagementException("Something went wrong while fetching the grade details.", e);
        }
    }

    /**
    * <p>
    * This method uses Hibernate to retrieve a particular record of standard and section available in the Grade table.
    * </p>
    * @param id
    *     the grade id (a unique identifier represents each standard and section) provided by the user.
    * @return Grade
    *     If found, returns standard and section with the students and teachers associated with it.
    *     Else returns null.
    * @throws SchoolManagementException 
    *      this happens when something goes wrong during data searching.
    */
    public Grade findGradeById(int id) throws SchoolManagementException {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Grade grade = session.get(Grade.class, id);
            if (null != grade) {
                Hibernate.initialize(grade.getStudents());
                Hibernate.initialize(grade.getTeachers());
            }
            return grade;
        } catch (HibernateException e) {
            throw new SchoolManagementException("Something went wrong while retrieving the grade details of ID " + id, e);
        }
    }

    /**
    * <p>
    * This method uses Hibernate to delete a particular record from the Grade table along with the students present in it.
    * </p>
    * @param id
    *     the grade id (a unique identifier represents each standard and section) provided by the user.
    * @return 
    *     returns true if the specified grade is successfully deleted or else returns false. 
    * @throws SchoolManagementException 
    *      this occurs when anything went wrong while removing data.
    */
    public boolean removeGrade(int id) throws SchoolManagementException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();            
            Grade grade = session.get(Grade.class, id);
            if (null != grade) {
                for (Student student : grade.getStudents()) {
                    session.delete(student);
                }
                session.delete(grade);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
            HibernateConnection.rollbackTransaction(transaction);
            throw new SchoolManagementException("Something went wrong while removing the grade details " + id, e);
        }
    }
}
