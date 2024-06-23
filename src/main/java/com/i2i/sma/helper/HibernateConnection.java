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
        String url = dotenv.get("DATABASE_URL");
        String uname = dotenv.get("DATABASE_UNAME");
        String password = dotenv.get("DATABASE_PSW");
        System.out.println("------------------saravana1");
        Configuration configuration = new Configuration().configure();
        System.out.println("------------------saravana2");

        configuration.setProperty("hibernate.connection.url", url);
        System.out.println("------------------saravana3");

        configuration.setProperty("hibernate.connection.username", uname);
        System.out.println("------------------saravana4");

        configuration.setProperty("hibernate.connection.password", password);
        System.out.println("------------------saravana5");

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
