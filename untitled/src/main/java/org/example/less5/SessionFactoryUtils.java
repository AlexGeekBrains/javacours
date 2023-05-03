package org.example.less5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtils {
    private static SessionFactoryUtils sessionFactoryUtils;
    private static SessionFactory factory;

    private SessionFactoryUtils() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static SessionFactoryUtils getSessionFactoryUtils() {
        if (sessionFactoryUtils == null) {
            sessionFactoryUtils = new SessionFactoryUtils();
        }
        return sessionFactoryUtils;
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
