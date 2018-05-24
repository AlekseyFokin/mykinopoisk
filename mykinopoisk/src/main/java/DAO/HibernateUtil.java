/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Фокин
 */
public class HibernateUtil {

    private static  SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;
    
 static {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void destroyServiceRegistry()
    {
    sessionFactory.close();
    StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}
