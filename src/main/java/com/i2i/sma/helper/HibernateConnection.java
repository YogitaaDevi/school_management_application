package com.i2i.sma.helper;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * <p>
 * This HibernateConnection class is responsible for creating and managing the Hibernate SessionFactory
 * to make our application interact with the database.
 * </p>
 */
public class HibernateConnection {
    private static SessionFactory sessionFactory;

    static {
        Dotenv dotenv = Dotenv.load();
        Configuration configuration = new Configuration().configure();
        configuration.setProperty("hibernate.connection.url", dotenv.get("DATABASE_URL"));
        configuration.setProperty("hibernate.connection.username", dotenv.get("DATABASE_UNAME"));
        configuration.setProperty("hibernate.connection.password", dotenv.get("DATABASE_PSW"));
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void rollbackTransaction(Transaction transaction) {
        try {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } catch (Exception e) {
            System.err.println("ERROR OCCURRED WHILE ROLLING BACK THE TRANSACTION: " + e);
        }
    }
}
