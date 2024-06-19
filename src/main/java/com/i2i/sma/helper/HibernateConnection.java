package com.i2i.sma.helper;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
*<p>
* This HibernateConnection class is responsible for creating 
* and managing the Hibernate SessionFactory.
*</p>
*/
public class HibernateConnection {
    private static final SessionFactory sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml").buildSessionFactory();

    /**
    * <p>
    * This method returns the sessionFactory inorder to connect with the database.
    * </p>
    * @return sessionFactory
    */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
    * <p>
    * This method is responsible to close the transaction that opens at any write operations in the database.
    * </p>
    * @param transaction
    *     transaction that opens at any write operations like create/update/delete.
    */
    public static void rollbackTransaction(Transaction transaction) {
        try {
            if (null != transaction) {
                transaction.rollback();
            }
        } catch(Exception e) {
            System.err.println("\nERROR OCCURED WHILE THE TRANSACTION ROLLBACK.");
        }
    }
}