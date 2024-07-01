package com.i2i.sma.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.helper.HibernateConnection;
import com.i2i.sma.models.Admin;


/**
 * <p>
 * This class is responsible for managing admin records.
 * It provides functionalities to add new admins and search for a admin
 * </p>
 */
@Repository
public class AdminDao {
    private static final Logger logger = LoggerFactory.getLogger(AdminDao.class);

    /**
     * <p>
     * This method makes use of Hibernate query language to add an admin record
     * to the database.
     * </p>
     * @param admin object
     *  contains admin's name and password in string. Only alphabets are allowed
     * @throws SchoolManagementException
     *   this happens when something goes wrong while adding a data.
     */
    public void insertAdminDetails(Admin admin) throws SchoolManagementException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            logger.debug("PROCESS STARTED: INSERTING ADMIN DETAILS OF NAME: {} IN THE DATABASE",
                    admin.getName());
            transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
        } catch (HibernateException e) {
            HibernateConnection.rollbackTransaction(transaction);
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE INSERTING " +
                                                "ADMIN DETAILS OF NAME " + admin.getName());
        }
    }

    /**
     * <p>
     * This method makes use of Hibernate query language to retrieve a particular admin record
     * from the database using the admin name and password.
     * </p>
     * @param name
     *   admin's name in string. Only alphabets are allowed
     * @param password
     *   admin's password in string. Can be alphabets or alphanumeric or numbers.
     * @return admin data
     *   contains admin ID(a unique identifier), name and password if is available.
     *   Else returns a empty admin.
     * @throws SchoolManagementException
     *   this happens when something goes wrong during searching for a data.
     */
    public Admin findAdmin(String name, String password) throws SchoolManagementException {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            logger.debug("PROCESS STARTED: FETCHING A ADMIN DETAILS FROM THE DATABASE", name);
            String retriveQuery = "FROM Admin WHERE name = :name AND password = :password";
            return session.createQuery(retriveQuery, Admin.class)
                          .setParameter("name", name).setParameter("password", password)
                          .uniqueResult();
        } catch (HibernateException e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING ADMIN DETAILS OF NAME " + name);
        }
    }

    /**
     * <p>
     * This method makes use of Hibernate query language
     * to retrieve all admin records from the database.
     * </p>
     * @return list of all student data
     * contains - 1.id(a unique identifier)
     *            2.name of the admins
     *            3.password of each admin
     * @throws SchoolManagementException
     * this happens when something goes wrong during data retrieving.
     */
    public List<Admin> getDetails() throws SchoolManagementException {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            logger.debug("PROCESS STARTED: FETCHING ALL ADMIN DETAILS FROM THE DATABASE");
            return session.createQuery("FROM Admin", Admin.class).list();
        } catch (HibernateException e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING ADMIN DETAILS");
        }
    }

    /**
     * <p>
     * This method makes use of Hibernate query language
     * to retrieve a particular admin record from the database using the admin ID.
     * </p>
     * @param id
     *   a unique identifier for each admin in integer.
     * @return admin data
     *    if the ID is available.
     *    Else returns an empty admin.
     * @throws SchoolManagementException
     *   this happens when something goes wrong during searching for a data.
     */
    public Admin findAdminById(int id) throws SchoolManagementException {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            logger.debug("PROCESS STARTED: FETCHING AN ADMIN DETAILS OF ID: {} FROM THE DATABASE", id);
            return session.get(Admin.class, id);
        } catch (HibernateException e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING THE ADMIN ID "
                    + id + " DETAILS.\nPLEASE TRY AGAIN...");
        }
    }

    /**
     * <p>
     * This method makes use of Hibernate query language
     * to delete a particular student record from the database using the student ID.
     * </p>
     *
     * @param id a unique identifier for each student in integer.
     * @return returns true if the student was successfully deleted or else returns false.
     * @throws SchoolManagementException this happens when something goes wrong during data removing.
     */
    public boolean isRemoveAdmin(int id) throws SchoolManagementException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            logger.debug("PROCESS STARTED: REMOVING A ADMIN DETAILS OF ID: {} FROM THE DATABASE", id);
            transaction = session.beginTransaction();
            Admin admin = session.get(Admin.class, id);
            if (null != admin) {
                session.delete(admin);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
            HibernateConnection.rollbackTransaction(transaction);
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE REMOVING THE ADMIN ID "
                    + id + "DETAILS.\nPLEASE TRY AGAIN...", e);
        }
    }
}