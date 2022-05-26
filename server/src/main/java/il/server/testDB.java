package il.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import il.entities.Employee;
import il.entities.Flower;
import il.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class testDB {
    public static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Flower.class).addAnnotatedClass(User.class);
        //configuration.addAnnotatedClass(Employee.class); //added this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void generateItems()throws Exception{
        Flower flower;

        flower = new Flower("whiteroses", 20,true,25,"flower", "white");
        CatalogControl.saveNewFlower(flower,"src/main/resources/images/whiteroses.jpeg" );

        flower = new Flower("sunflower", 23,true, 5, "flower", "yellow");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/sunflower.jpeg");

        flower = new Flower("chinaFlower", 20,false, 0, "flower", "red");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/chinaFlower.jpeg");

        flower = new Flower("pin", 20,false, 0,"flower", "pink");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/pin.jpeg");

        flower = new Flower("whiteroses", 20,true, 50, "flower", "white");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/whiteroses.jpeg");

        flower = new Flower("sunflower", 20,true, 50, "flower", "yellow");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/sunflower.jpeg");

        session.flush();
        session.getTransaction().commit(); // Save everything.
    }

//    //added this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    private static void generateEmployee()throws Exception {
//
//        Employee employee;
//
//        employee = new Employee("Malki Grossman", "malki123456" , "123456789", 1 , "All");
//
//        employee = new Employee("Shir Snea", "shir123456" , "123456789", 2 , "All");
//
//        employee = new Employee("Liran Eliav", "liran123456" , "123456789", 3 , "Store 1");
//
//        employee = new Employee("Dean Amar", "dean123456" , "123456789", 3 , "Store 2");
//
//        employee = new Employee("Ido Shitrit", "ido123456" , "123456789", 3 , "Store 3");
//
//        employee = new Employee("Roie Shahar", "roie123456" , "123456789", 3 , "Store 4");
//
//        employee = new Employee("Shahar Tavor", "shahar123456" , "123456789", 5 , "Store 1");
//
//        employee = new Employee("Itai Zeitony", "itai123456" , "123456789", 5 , "Store 2");
//
//        employee = new Employee("Shira Tzadok", "shira123456" , "123456789", 5 , "Store 3");
//
//        employee = new Employee("Gal Some", "gal123456" , "123456789", 5 , "Store 4");
//
//        employee = new Employee("Saar Gorman", "saar123456" , "123456789", 4 , "All");
//
//        employee = new Employee("Demian Brom", "demian123456" , "123456789", 4 , "All");
//
//        employee = new Employee("Shani Koren", "shanik98" , "123456789", 5 , "Store 1");
//
//        employee = new Employee("Shani Amar", "shani123456" , "123456789", 5 , "Store 2");
//
//        employee = new Employee("Aviv Shitrit", "aviv123456" , "123456789", 5 , "Store 3");
//
//        session.flush();
//        session.getTransaction().commit(); // Save everything.
//    }

    public static void openSssion(){
        try {
            System.out.println("open session to mySQL");
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error: cant open session to mySQL.");
            exception.printStackTrace();
        }
    }

    public static void initMySQL(){
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.out.println("open session to mySQL");
            generateItems();
           // generateEmployee(); //added this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error: cant init mySQL!");
            exception.printStackTrace();
        } finally {
            session.close();
            System.out.println("close session to mySQL");
            System.out.println("init mySQL!");
        }
    }

    public static void closeSession(){
        try {
            if (session != null) {
                session.getTransaction().rollback();
            }
            session.close();
            System.out.println("close session to mySQL");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}