package com.i2i.sma.helper;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
*<p>
* This HibernateConnection class is responsible for creating and managing the Hibernate SessionFactory
* to make our application interact with the database.
*</p>
*/
public class HibernateConnection {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void rollbackTransaction(Transaction transaction) {
        try {
            if (null != transaction || transaction.isActive()) {
                transaction.rollback();
            }
        } catch(Exception e) {
            System.err.println("\nERROR OCCURED WHILE THE TRANSACTION ROLLBACK.");
        }
    }
}