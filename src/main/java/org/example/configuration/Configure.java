/*
package org.example.configuration;

import lk.ijse.d24hostalmng.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class Configure {
    private static Configure configure;
    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.Properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        configuration.
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Room.class).
                addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(User.class);

        factory=configuration.setProperties(properties).buildSessionFactory();
    }

    private Configure(){}

    public static Configure getInstance(){
        return configure == null ? new Configure() : configure;
    }

    public Session getSession(){
        return factory.openSession();
    }
}
*/
