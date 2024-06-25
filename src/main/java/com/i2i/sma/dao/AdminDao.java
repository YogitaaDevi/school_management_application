package com.i2i.sma.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.helper.HibernateConnection;
import com.i2i.sma.models.Admin;


/**
 * <p>
 * This class is responsible for managing admin records.
 * It provides functionalities to add new admins and search for a admin
 * </p>
 */
public class AdminDao {

    /**
     * <p>
     * This method makes use of Hibernate query language to add an admin record
     * to the database.
     * </p>
     * @param name
     *   admin's name in string. Only alphabets are allowed
     * @param password
     *   admin's password in string. Can be alphabets or alphanumeric or numbers.
     * @throws SchoolManagementException
     *   this happens when something goes wrong while adding a data.
     */
    public void insertAdminDetails(Admin admin) throws SchoolManagementException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
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
            String retriveQuery = "FROM Admin WHERE name = :name AND password = :password";
            return session.createQuery(retriveQuery, Admin.class)
                          .setParameter("name", name).setParameter("password", password)
                          .uniqueResult();
        } catch (HibernateException e) {
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING ADMIN DETAILS OF NAME " + name);
        }
    }
}